package si.um.feri.aiv.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.aiv.chain.AvailabilityCheckHandler;
import si.um.feri.aiv.chain.BalanceCheckHandler;
import si.um.feri.aiv.chain.ChargingValidationHandler;
import si.um.feri.aiv.chain.CompatibilityCheckHandler;
import si.um.feri.aiv.dao.interfaces.ChargingStationDAOInterface;
import si.um.feri.aiv.dao.interfaces.ProviderDAOInterface;
import si.um.feri.aiv.observer.ChargingStationDisplay;
import si.um.feri.aiv.observer.ProviderNotifier;
import si.um.feri.aiv.observer.UserNotifier;
import si.um.feri.aiv.remote.ChargingValidationRemote;
import si.um.feri.aiv.remote.ChargingValidationResult;
import si.um.feri.aiv.service.interfaces.ChargingStationServiceLocal;
import si.um.feri.aiv.service.interfaces.UserServiceLocal;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import si.um.feri.aiv.vao.User;

import java.util.List;
import java.util.Optional;

@Stateless
public class ChargingStationService implements ChargingStationServiceLocal, ChargingValidationRemote {

    private static final double COST_PER_KW = 0.20;

    @EJB
    private ChargingStationDAOInterface stationDAO;

    @EJB
    private UserServiceLocal userService;

    @EJB
    private ProviderDAOInterface providerDAO;

    private final ProviderNotifier providerNotifier = new ProviderNotifier();
    private final ChargingStationDisplay displayNotifier = new ChargingStationDisplay();
    private final UserNotifier userNotifier = new UserNotifier();

    @Override
    public void addStation(ChargingStation station) throws IllegalArgumentException {
        if (station == null) {
            throw new IllegalArgumentException("Station cannot be null");
        }
        if (station.getId() == null || station.getId().isEmpty()) {
            throw new IllegalArgumentException("ID is required");
        }
        if (station.getPowerKw() <= 0) {
            throw new IllegalArgumentException("Power must be positive");
        }
        if (stationDAO.getStationById(station.getId()).isPresent()) {
            throw new IllegalArgumentException("Station with this ID already exists");
        }

        // Basic observer set is attached when a station enters the system.
        station.addObserver(providerNotifier);
        station.addObserver(displayNotifier);
        station.addObserver(userNotifier);
        stationDAO.insertStation(station);
    }

    @Override
    public List<ChargingStation> getStationsByProvider(Provider provider) {
        if (provider == null) return List.of();
        return stationDAO.getStationsByProvider(provider);
    }

    @Override
    public List<ChargingStation> getAllStations() {
        return stationDAO.getAllStations();
    }

    @Override
    public void updateStationPower(String id, double newPower) {
        if (newPower <= 0) {
            throw new IllegalArgumentException("Power must be positive");
        }
        stationDAO.updateStation(id, newPower);
    }

    @Override
    public void updateStation(String id, String location, String connectorType, double newPower, String providerEmail, boolean active, String region) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID is required");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location is required");
        }
        if (connectorType == null || connectorType.isBlank()) {
            throw new IllegalArgumentException("Connector type is required");
        }
        if (newPower <= 0) {
            throw new IllegalArgumentException("Power must be positive");
        }
        if (region == null || region.isBlank()) {
            throw new IllegalArgumentException("Region is required");
        }

        Provider provider = providerDAO.getProviderByEmail(providerEmail)
                .orElseThrow(() -> new IllegalArgumentException("Provider not found"));
        stationDAO.updateStation(id, location, connectorType, newPower, provider, active, region);
    }

    @Override
    public void startCharging(String stationId, String userEmail) {
        ChargingValidationContext context = resolveContext(stationId, userEmail);
        validateOrThrow(context);
        userService.decreaseBalance(context.user.getEmail(), context.estimatedCost);
        context.station.startCharging(userEmail, stationDAO.getAllStations());
    }

    @Override
    public void finishCharging(String stationId) {
        ChargingStation station = stationDAO.getStationById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found"));

        station.stopCharging(stationDAO.getAllStations());
    }

    @Override
    public Optional<ChargingStation> getStationById(String id) {
        return stationDAO.getStationById(id);
    }

    @Override
    public void deleteStation(String id) {
        stationDAO.deleteStation(id);
    }

    @Override
    public ChargingValidationResult validateCharging(String stationId, String userEmail) {
        try {
            ChargingValidationContext context = resolveContext(stationId, userEmail);
            validateOrThrow(context);
            return ChargingValidationResult.allowed(context.station.getId(), context.user.getEmail(), context.estimatedCost);
        } catch (RuntimeException e) {
            double estimatedCost = 0;
            try {
                estimatedCost = resolveContext(stationId, userEmail).estimatedCost;
            } catch (RuntimeException ignored) {
                // keep zero when station/user cannot be resolved
            }
            return ChargingValidationResult.denied(stationId, userEmail, e.getMessage(), estimatedCost);
        }
    }

    private ChargingValidationContext resolveContext(String stationId, String userEmail) {
        ChargingStation station = stationDAO.getStationById(stationId)
                .orElseThrow(() -> new IllegalArgumentException("Station not found"));
        User user = userService.getUserByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        double estimatedCost = station.getPowerKw() * COST_PER_KW;
        return new ChargingValidationContext(station, user, estimatedCost);
    }

    private void validateOrThrow(ChargingValidationContext context) {
        ChargingValidationHandler chain = new AvailabilityCheckHandler();
        chain.setNext(new BalanceCheckHandler())
                .setNext(new CompatibilityCheckHandler());
        chain.handle(context.station, context.user, context.estimatedCost);
    }

    private record ChargingValidationContext(ChargingStation station, User user, double estimatedCost) {
    }
}
