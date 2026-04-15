package si.um.feri.aiv.observer;

import si.um.feri.aiv.vao.ChargingStation;

import java.util.List;
import java.util.stream.Collectors;

public class ChargingStationDisplay implements ChargingStationObserver {

    @Override
    public void update(ChargingStation station, String affectedUserEmail, List<ChargingStation> allStations) {
        String freeStations = allStations.stream()
                .filter(s -> !s.isOccupied())
                .map(ChargingStation::getLocation)
                .collect(Collectors.joining(", "));

        String occupiedStations = allStations.stream()
                .filter(ChargingStation::isOccupied)
                .map(ChargingStation::getLocation)
                .collect(Collectors.joining(", "));

        System.out.println("[Zaslon polnilne postaje] Trenutno stanje polnilnic:");
        System.out.println("Proste polnilnice: " + (freeStations.isBlank() ? "-" : freeStations));
        System.out.println("Zasedene polnilnice: " + (occupiedStations.isBlank() ? "-" : occupiedStations));
        System.out.println("-------------------------------------------------");
    }
}

