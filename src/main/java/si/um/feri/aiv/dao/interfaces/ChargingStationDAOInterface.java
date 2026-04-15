package si.um.feri.aiv.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import java.util.List;
import java.util.Optional;

@Local
public interface ChargingStationDAOInterface {
    void insertStation(ChargingStation station);
    List<ChargingStation> getAllStations();
    List<ChargingStation> getStationsByProvider(Provider provider);
    Optional<ChargingStation> getStationById(String id);
    void updateStation(String id, double newPowerKw);
    void updateStation(String id, String location, String connectorType, double newPowerKw, Provider provider, boolean active, String region);
    void deleteStation(String id);
}

