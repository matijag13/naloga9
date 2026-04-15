package si.um.feri.aiv.remote;

import java.io.Serializable;
import java.io.Serial;

public class ChargingValidationResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean allowed;
    private String message;
    private String stationId;
    private String userEmail;
    private double estimatedCost;

    public ChargingValidationResult() {
    }

    public ChargingValidationResult(boolean allowed, String message, String stationId, String userEmail, double estimatedCost) {
        this.allowed = allowed;
        this.message = message;
        this.stationId = stationId;
        this.userEmail = userEmail;
        this.estimatedCost = estimatedCost;
    }

    public static ChargingValidationResult allowed(String stationId, String userEmail, double estimatedCost) {
        return new ChargingValidationResult(true, "Polnjenje je dovoljeno.", stationId, userEmail, estimatedCost);
    }

    public static ChargingValidationResult denied(String stationId, String userEmail, String message, double estimatedCost) {
        return new ChargingValidationResult(false, message, stationId, userEmail, estimatedCost);
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    @Override
    public String toString() {
        return "ChargingValidationResult{" +
                "allowed=" + allowed +
                ", message='" + message + '\'' +
                ", stationId='" + stationId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", estimatedCost=" + estimatedCost +
                '}';
    }
}


