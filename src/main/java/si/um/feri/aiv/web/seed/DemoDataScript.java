package si.um.feri.aiv.web.seed;

import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import si.um.feri.aiv.vao.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class DemoDataScript {

    private DemoDataScript() {
    }

    public static List<Provider> providers() {
        List<Provider> providers = new ArrayList<>();
        providers.add(new Provider("Elektro Maribor", "kontakt@elektro-mb.si", "Ulica Heroja Slandra 10, Maribor"));
        providers.add(new Provider("ePolnilnice", "info@epolnilnice.si", "Dunajska cesta 45, Ljubljana"));
        providers.add(new Provider("Petrol eCharge", "podpora@petrol.si", "Dunajska cesta 50, Ljubljana"));
        return providers;
    }

    public static List<ChargingStation> stationsFor(List<Provider> providers) {
        Map<String, Provider> providersByEmail = new LinkedHashMap<>();
        for (Provider provider : providers) {
            providersByEmail.put(provider.getEmail(), provider);
        }

        List<ChargingStation> stations = new ArrayList<>();
        stations.add(new ChargingStation("MB-001", "Europark Maribor", "CCS", 50.0, providersByEmail.get("kontakt@elektro-mb.si"), true, "Podravska"));
        stations.add(new ChargingStation("MB-002", "Glavni trg Maribor", "Type2", 22.0, providersByEmail.get("kontakt@elektro-mb.si"), true, "Podravska"));
        stations.add(new ChargingStation("LJ-001", "BTC Ljubljana", "CCS", 150.0, providersByEmail.get("info@epolnilnice.si"), true, "Osrednjeslovenska"));
        stations.add(new ChargingStation("CE-001", "Celje Center", "Type2", 43.0, providersByEmail.get("podpora@petrol.si"), true, "Savinjska"));
        return stations;
    }

    public static List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User("Miha Novak", "miha.novak@mail.si", 75.0, "CCS"));
        users.add(new User("Nina Kranjc", "nina.kranjc@mail.si", 42.5, "Type2"));
        users.add(new User("Tina Rozman", "tina.rozman@mail.si", 120.0, "CCS"));
        return users;
    }
}
