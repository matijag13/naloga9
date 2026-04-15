package si.um.feri.aiv.observer;

import si.um.feri.aiv.vao.ChargingStation;

import java.util.List;

public class UserNotifier implements ChargingStationObserver {

    @Override
    public void update(ChargingStation station, String affectedUserEmail, List<ChargingStation> allStations) {
        if (affectedUserEmail == null || affectedUserEmail.isBlank()) {
            return;
        }

        String providerName = station.getProvider() != null ? station.getProvider().getName() : "Ponudnik";
        if (station.isOccupied()) {
            System.out.println("[EMAIL] Od: noreply@chargingstations.com");
            System.out.println("[EMAIL] Za: " + affectedUserEmail);
            System.out.println("[EMAIL] Zadeva: Polnjenje se je zacelo!");
            System.out.println("Pozdravljeni, vase polnjenje na polnilnici " + station.getLocation() + " se je uspesno zacelo.");
            System.out.println("Moc polnjenja: " + station.getPowerKw() + " kW");
            System.out.println("Lep pozdrav, " + providerName);
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("[EMAIL] Od: noreply@chargingstations.com");
            System.out.println("[EMAIL] Za: " + affectedUserEmail);
            System.out.println("[EMAIL] Zadeva: Polnjenje koncano!");
            System.out.println("Pozdravljeni, vase polnjenje na polnilnici " + station.getLocation() + " je koncano.");
            System.out.println("Lep pozdrav, " + providerName);
            System.out.println("-------------------------------------------------");
        }
    }
}

