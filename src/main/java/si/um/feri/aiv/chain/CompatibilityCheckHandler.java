package si.um.feri.aiv.chain;

import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.User;

public class CompatibilityCheckHandler extends ChargingValidationHandler {

    @Override
    protected void check(ChargingStation station, User user, double estimatedCost) {
        if (!station.getConnectorType().equalsIgnoreCase(user.getCarType())) {
            throw new IllegalStateException("Vozilo ni kompatibilno s polnilnico");
        }
    }
}

