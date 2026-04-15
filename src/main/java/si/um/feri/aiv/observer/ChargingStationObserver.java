package si.um.feri.aiv.observer;

import si.um.feri.aiv.vao.ChargingStation;

import java.util.List;

public interface ChargingStationObserver {
    void update(ChargingStation station, String affectedUserEmail, List<ChargingStation> allStations);
}

