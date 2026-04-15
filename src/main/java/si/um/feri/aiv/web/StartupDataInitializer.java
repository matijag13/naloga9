package si.um.feri.aiv.web;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import si.um.feri.aiv.service.interfaces.ChargingStationServiceLocal;
import si.um.feri.aiv.service.interfaces.ProviderServiceLocal;
import si.um.feri.aiv.service.interfaces.UserServiceLocal;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import si.um.feri.aiv.vao.User;
import si.um.feri.aiv.web.seed.DemoDataScript;

import java.util.List;

@ApplicationScoped
public class StartupDataInitializer {

    @EJB
    private ProviderServiceLocal providerService;

    @EJB
    private ChargingStationServiceLocal chargingStationService;

    @EJB
    private UserServiceLocal userService;

    // In CDI, @ApplicationScoped beans can be lazy; observing initialization forces early setup.
    public void onApplicationStart(@Observes @Initialized(ApplicationScoped.class) Object event) {
        init();
    }

    @PostConstruct
    public void init() {
        // Prevent duplicate demo data when the application context is re-initialized.
        if (!providerService.getAllProviders().isEmpty() || !chargingStationService.getAllStations().isEmpty()) {
            return;
        }

        List<Provider> providers = DemoDataScript.providers();
        for (Provider provider : providers) {
            providerService.addProvider(provider);
        }

        for (ChargingStation station : DemoDataScript.stationsFor(providers)) {
            chargingStationService.addStation(station);
        }

        for (User user : DemoDataScript.users()) {
            userService.addUser(user);
        }

        System.out.println("Inicializacija testnih podatkov koncana: ponudniki, polnilne postaje in uporabniki so nalozeni.");
    }
}
