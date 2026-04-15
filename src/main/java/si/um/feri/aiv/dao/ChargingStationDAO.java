package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.dao.interfaces.ChargingStationDAOInterface;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import java.util.List;
import java.util.Optional;

@Stateless
public class ChargingStationDAO implements ChargingStationDAOInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertStation(ChargingStation station) {
        if (station == null) {
            throw new IllegalArgumentException("Station cannot be null");
        }
        if (station.getProvider() == null) {
            throw new IllegalArgumentException("Provider is required");
        }

        Provider provider = em.find(Provider.class, station.getProvider().getEmail());
        if (provider == null) {
            throw new IllegalArgumentException("Provider not found");
        }

        provider.addStation(station);
        em.persist(station);
    }

    @Override
    public List<ChargingStation> getAllStations() {
        return em.createQuery("SELECT s FROM ChargingStation s ORDER BY s.id", ChargingStation.class)
                .getResultList();
    }

    @Override
    public List<ChargingStation> getStationsByProvider(Provider provider) {
        if (provider == null || provider.getEmail() == null) {
            return List.of();
        }

        return em.createQuery("SELECT s FROM ChargingStation s WHERE s.provider.email = :email ORDER BY s.id", ChargingStation.class)
                .setParameter("email", provider.getEmail())
                .getResultList();
    }

    @Override
    public Optional<ChargingStation> getStationById(String id) {
        return Optional.ofNullable(em.find(ChargingStation.class, id));
    }

    @Override
    public void updateStation(String id, double newPowerKw) {
        ChargingStation station = em.find(ChargingStation.class, id);
        if (station == null) {
            throw new IllegalArgumentException("Station not found");
        }

        station.setPowerKw(newPowerKw);
    }

    public void updateStation(String id, String location, String connectorType, double newPowerKw, Provider provider, boolean active, String region) {
        ChargingStation station = em.find(ChargingStation.class, id);
        if (station == null) {
            throw new IllegalArgumentException("Station not found");
        }
        if (provider == null || provider.getEmail() == null) {
            throw new IllegalArgumentException("Provider is required");
        }

        Provider managedProvider = em.find(Provider.class, provider.getEmail());
        if (managedProvider == null) {
            throw new IllegalArgumentException("Provider not found");
        }

        Provider oldProvider = station.getProvider();
        if (oldProvider != null && !oldProvider.equals(managedProvider)) {
            oldProvider.removeStation(station);
        }

        station.setLocation(location);
        station.setConnectorType(connectorType);
        station.setPowerKw(newPowerKw);
        station.setActive(active);
        station.setRegion(region);
        managedProvider.addStation(station);
    }

    @Override
    public void deleteStation(String id) {
        ChargingStation station = em.find(ChargingStation.class, id);
        if (station != null) {
            Provider provider = station.getProvider();
            if (provider != null) {
                provider.removeStation(station);
            }
            em.remove(station);
        }
    }
}

