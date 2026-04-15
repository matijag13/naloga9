package si.um.feri.aiv.remote;

import jakarta.ejb.Remote;
import si.um.feri.aiv.remote.ChargingValidationResult;

@Remote
public interface ChargingValidationRemote {
    ChargingValidationResult validateCharging(String stationId, String userEmail);
}


