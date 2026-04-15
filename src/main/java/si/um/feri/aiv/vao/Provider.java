package si.um.feri.aiv.vao;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "providers")
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 500)
    private String address;

    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargingStation> stations = new ArrayList<>();

    public Provider() {
    }

    public Provider(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<ChargingStation> getStations() { return stations; }

    public void addStation(ChargingStation station) {
        if (station != null && !stations.contains(station)) {
            stations.add(station);
            station.setProvider(this);
        }
    }

    public void removeStation(ChargingStation station) {
        if (station != null && stations.remove(station) && station.getProvider() == this) {
            station.setProvider(null);
        }
    }

    @Override
    public String toString() {
        return "Provider{name='" + name + "', email='" + email + "', address='" + address + "', stations_count=" + stations.size() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(email, provider.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

