package si.um.feri.aiv.observer;

import si.um.feri.aiv.vao.ChargingStation;

import java.util.List;

public class ProviderNotifier implements ChargingStationObserver {

    @Override
    public void update(ChargingStation station, String affectedUserEmail, List<ChargingStation> allStations) {
        String providerName = station.getProvider() != null ? station.getProvider().getName() : "Neznan ponudnik";
        String status = station.isOccupied() ? "zasedena" : "prosta";

        System.out.println("Ponudnik obvescen: Polnilnica " + station.getLocation() + " pri ponudniku "
                + providerName + " je zdaj " + status + ".");
    }
}

