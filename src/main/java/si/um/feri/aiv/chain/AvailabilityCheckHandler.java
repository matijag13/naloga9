package si.um.feri.aiv.chain;

import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.User;

public class AvailabilityCheckHandler extends ChargingValidationHandler {

    @Override
    protected void check(ChargingStation station, User user, double estimatedCost) {
        if (!station.isActive()) {
            throw new IllegalStateException("Polnilnica ni aktivna");
        }
        if (station.isOccupied()) {
            throw new IllegalStateException("Polnilnica je ze zasedena");
        }
    }
}

