package si.um.feri.aiv.service.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;

import java.util.List;
import java.util.Optional;

@Local
public interface ChargingStationServiceLocal {
    void addStation(ChargingStation station);
    List<ChargingStation> getStationsByProvider(Provider provider);
    List<ChargingStation> getAllStations();
    void updateStationPower(String id, double newPower);
    void updateStation(String id, String location, String connectorType, double newPower, String providerEmail, boolean active, String region);
    void startCharging(String stationId, String userEmail);
    void finishCharging(String stationId);
    Optional<ChargingStation> getStationById(String id);
    void deleteStation(String id);
}


