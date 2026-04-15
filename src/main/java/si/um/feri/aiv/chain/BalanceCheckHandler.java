package si.um.feri.aiv.chain;

import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.User;

public class BalanceCheckHandler extends ChargingValidationHandler {

    @Override
    protected void check(ChargingStation station, User user, double estimatedCost) {
        if (user.getBalance() < estimatedCost) {
            throw new IllegalStateException("Uporabnik nima dovolj sredstev (potrebno: " + estimatedCost + ")");
        }
    }
}

