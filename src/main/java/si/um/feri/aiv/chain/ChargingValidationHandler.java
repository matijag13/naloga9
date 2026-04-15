package si.um.feri.aiv.chain;

import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.User;

public abstract class ChargingValidationHandler {
    private ChargingValidationHandler next;

    public ChargingValidationHandler setNext(ChargingValidationHandler next) {
        this.next = next;
        return next;
    }

    public final void handle(ChargingStation station, User user, double estimatedCost) {
        check(station, user, estimatedCost);
        if (next != null) {
            next.handle(station, user, estimatedCost);
        }
    }

    protected abstract void check(ChargingStation station, User user, double estimatedCost);
}

