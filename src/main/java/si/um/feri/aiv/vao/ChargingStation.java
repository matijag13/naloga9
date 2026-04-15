package si.um.feri.aiv.vao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import si.um.feri.aiv.observer.ChargingStationObserver;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "charging_stations")
public class ChargingStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, length = 100)
    private String id;

    @Column(nullable = false, length = 255)
    private String location;

    @Column(name = "connector_type", nullable = false, length = 100)
    private String connectorType;

    @Column(name = "power_kw", nullable = false)
    private double powerKw;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_email", nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false)
    private boolean occupied;

    @Column(name = "current_user_email", length = 255)
    private String currentUserEmail;

    @Transient
    private final List<ChargingStationObserver> observers;

    public ChargingStation() {
        this.observers = new ArrayList<>();
    }

    public ChargingStation(String id, String location, String connectorType, double powerKw, Provider provider, boolean active, String region) {
        this.id = id;
        this.location = location;
        this.connectorType = connectorType;
        this.powerKw = powerKw;
        this.provider = provider;
        this.active = active;
        this.region = region;
        this.occupied = false;
        this.currentUserEmail = null;
        this.observers = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getConnectorType() { return connectorType; }
    public void setConnectorType(String connectorType) { this.connectorType = connectorType; }

    public double getPowerKw() { return powerKw; }
    public void setPowerKw(double powerKw) { this.powerKw = powerKw; }

    public Provider getProvider() { return provider; }
    public void setProvider(Provider provider) { this.provider = provider; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public boolean isOccupied() { return occupied; }

    public String getCurrentUserEmail() { return currentUserEmail; }

    public void addObserver(ChargingStationObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(ChargingStationObserver observer) {
        observers.remove(observer);
    }

    public void startCharging(String userEmail, List<ChargingStation> allStations) {
        if (userEmail == null || userEmail.isBlank()) {
            throw new IllegalArgumentException("User email is required");
        }

        this.occupied = true;
        this.currentUserEmail = userEmail;
        notifyObservers(userEmail, allStations);
    }

    public void stopCharging(List<ChargingStation> allStations) {
        if (!occupied) {
            throw new IllegalStateException("Station is already free");
        }

        String finishedUserEmail = this.currentUserEmail;
        this.occupied = false;
        this.currentUserEmail = null;
        notifyObservers(finishedUserEmail, allStations);
    }

    private void notifyObservers(String affectedUserEmail, List<ChargingStation> allStations) {
        for (ChargingStationObserver observer : observers) {
            observer.update(this, affectedUserEmail, allStations);
        }
    }

    @Override
    public String toString() {
        return "ChargingStation{id='" + id + "', location='" + location + "', type='" + connectorType + "', power=" + powerKw + "kW, provider=" + (provider != null ? provider.getName() : "None") + ", active=" + active + ", occupied=" + occupied + ", currentUserEmail='" + currentUserEmail + "', region='" + region + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingStation that = (ChargingStation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
