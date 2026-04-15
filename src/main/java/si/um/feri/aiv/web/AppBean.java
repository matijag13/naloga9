package si.um.feri.aiv.web;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import si.um.feri.aiv.service.interfaces.ChargingStationServiceLocal;
import si.um.feri.aiv.service.interfaces.ProviderServiceLocal;
import si.um.feri.aiv.service.interfaces.UserServiceLocal;
import si.um.feri.aiv.vao.ChargingStation;
import si.um.feri.aiv.vao.Provider;
import si.um.feri.aiv.vao.User;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Named("app")
@SessionScoped
public class AppBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private transient UserServiceLocal userService;

    @EJB
    private transient ProviderServiceLocal providerService;

    @EJB
    private transient ChargingStationServiceLocal chargingStationService;

    private String message = "JSF je pripravljen.";

    private String userName;
    private String userEmail;
    private String userCarType;
    private double userBalance;
    private boolean userEditMode;
    private String userOriginalEmail;
    private boolean userDeleteDialogVisible;
    private String userDeleteEmail;
    private String userDeleteName;

    private String providerName;
    private String providerEmail;
    private String providerAddress;
    private boolean providerEditMode;
    private String providerOriginalEmail;
    private boolean providerDeleteDialogVisible;
    private String providerDeleteEmail;
    private String providerDeleteName;

    private String stationId;
    private String stationLocation;
    private String stationConnectorType;
    private double stationPowerKw;
    private String stationProviderEmail;
    private boolean stationActive = true;
    private String stationRegion;
    private boolean stationEditMode;
    private String stationOriginalId;
    private boolean stationDeleteDialogVisible;
    private String stationDeleteId;
    private String stationDeleteName;

    @PostConstruct
    public void init() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUserEditMode() {
        return userEditMode;
    }

    public boolean isProviderEditMode() {
        return providerEditMode;
    }

    public boolean isStationEditMode() {
        return stationEditMode;
    }

    public boolean isUserDeleteDialogVisible() {
        return userDeleteDialogVisible;
    }

    public boolean isProviderDeleteDialogVisible() {
        return providerDeleteDialogVisible;
    }

    public boolean isStationDeleteDialogVisible() {
        return stationDeleteDialogVisible;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCarType() {
        return userCarType;
    }

    public void setUserCarType(String userCarType) {
        this.userCarType = userCarType;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public String getUserOriginalEmail() {
        return userOriginalEmail;
    }

    public String getUserDeleteEmail() {
        return userDeleteEmail;
    }

    public String getUserDeleteName() {
        return userDeleteName;
    }

    public String getUserFormTitle() {
        return userEditMode ? "Urejanje uporabnika" : "Dodajanje uporabnika";
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderOriginalEmail() {
        return providerOriginalEmail;
    }

    public String getProviderDeleteEmail() {
        return providerDeleteEmail;
    }

    public String getProviderDeleteName() {
        return providerDeleteName;
    }

    public String getProviderFormTitle() {
        return providerEditMode ? "Urejanje ponudnika" : "Dodajanje ponudnika";
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationLocation() {
        return stationLocation;
    }

    public void setStationLocation(String stationLocation) {
        this.stationLocation = stationLocation;
    }

    public String getStationConnectorType() {
        return stationConnectorType;
    }

    public void setStationConnectorType(String stationConnectorType) {
        this.stationConnectorType = stationConnectorType;
    }

    public double getStationPowerKw() {
        return stationPowerKw;
    }

    public void setStationPowerKw(double stationPowerKw) {
        this.stationPowerKw = stationPowerKw;
    }

    public String getStationProviderEmail() {
        return stationProviderEmail;
    }

    public void setStationProviderEmail(String stationProviderEmail) {
        this.stationProviderEmail = stationProviderEmail;
    }

    public boolean isStationActive() {
        return stationActive;
    }

    public void setStationActive(boolean stationActive) {
        this.stationActive = stationActive;
    }

    public String getStationRegion() {
        return stationRegion;
    }

    public void setStationRegion(String stationRegion) {
        this.stationRegion = stationRegion;
    }

    public String getStationOriginalId() {
        return stationOriginalId;
    }

    public String getStationDeleteId() {
        return stationDeleteId;
    }

    public String getStationDeleteName() {
        return stationDeleteName;
    }

    public String getStationFormTitle() {
        return stationEditMode ? "Urejanje polnilnice" : "Dodajanje polnilnice";
    }

    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    public List<Provider> getProviders() {
        return providerService.getAllProviders();
    }

    public List<ChargingStation> getStations() {
        return chargingStationService.getAllStations();
    }

    public int getUserCount() {
        return getUsers().size();
    }

    public int getProviderCount() {
        return getProviders().size();
    }

    public int getStationCount() {
        return getStations().size();
    }

    public String getHomeTitle() {
        return "Upravljanje ponudnikov, polnilnic in uporabnikov";
    }

    public String goHome() {
        message = "Pregled nad podatki je pripravljen.";
        return "/index.xhtml?faces-redirect=true";
    }

    public String goUsers() {
        message = "Pregled uporabnikov.";
        return "/users.xhtml?faces-redirect=true";
    }

    public String goProviders() {
        message = "Pregled ponudnikov.";
        return "/providers.xhtml?faces-redirect=true";
    }

    public String goStations() {
        message = "Pregled polnilnic.";
        return "/stations.xhtml?faces-redirect=true";
    }

    public String newUser() {
        resetUserForm();
        userEditMode = false;
        message = "Vnesite podatke novega uporabnika.";
        return "/user-form.xhtml?faces-redirect=true";
    }

    public String editUser(User user) {
        if (user == null) {
            message = "Izbran uporabnik ne obstaja.";
            return null;
        }

        userOriginalEmail = user.getEmail();
        userName = user.getName();
        userEmail = user.getEmail();
        userCarType = user.getCarType();
        userBalance = user.getBalance();
        userEditMode = true;
        message = "Urejanje uporabnika je pripravljeno.";
        return "/user-form.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        try {
            if (userEditMode) {
                if (userOriginalEmail == null || userOriginalEmail.isBlank()) {
                    throw new IllegalStateException("Manjka izvorni email uporabnika.");
                }
                if (!Objects.equals(userOriginalEmail, userEmail)) {
                    throw new IllegalArgumentException("Email uporabnika ni mogoče spreminjati.");
                }
                userService.updateUser(userOriginalEmail, userName, userBalance, userCarType);
                message = "Uporabnik je posodobljen.";
            } else {
                userService.addUser(new User(userName, userEmail, userBalance, userCarType));
                message = "Uporabnik je dodan.";
            }

            resetUserForm();
            return "/users.xhtml?faces-redirect=true";
        } catch (Exception e) {
            message = "Napaka pri shranjevanju uporabnika: " + e.getMessage();
            return null;
        }
    }

    public String cancelUserForm() {
        resetUserForm();
        message = "Vnos uporabnika je preklican.";
        return "/users.xhtml?faces-redirect=true";
    }

    public String requestDeleteUser(User user) {
        if (user == null) {
            message = "Izbran uporabnik ne obstaja.";
            return null;
        }

        userDeleteEmail = user.getEmail();
        userDeleteName = user.getName();
        userDeleteDialogVisible = true;
        message = "Potrdite brisanje uporabnika.";
        return null;
    }

    public String confirmDeleteUser() {
        try {
            if (userDeleteEmail != null && !userDeleteEmail.isBlank()) {
                userService.deleteUser(userDeleteEmail);
                message = "Uporabnik je izbrisan.";
            }
        } catch (Exception e) {
            message = "Napaka pri brisanju uporabnika: " + e.getMessage();
        } finally {
            cancelDeleteUser();
        }

        return "/users.xhtml?faces-redirect=true";
    }

    public String cancelDeleteUser() {
        userDeleteDialogVisible = false;
        userDeleteEmail = null;
        userDeleteName = null;
        return null;
    }

    public String newProvider() {
        resetProviderForm();
        providerEditMode = false;
        message = "Vnesite podatke novega ponudnika.";
        return "/provider-form.xhtml?faces-redirect=true";
    }

    public String editProvider(Provider provider) {
        if (provider == null) {
            message = "Izbran ponudnik ne obstaja.";
            return null;
        }

        providerOriginalEmail = provider.getEmail();
        providerName = provider.getName();
        providerEmail = provider.getEmail();
        providerAddress = provider.getAddress();
        providerEditMode = true;
        message = "Urejanje ponudnika je pripravljeno.";
        return "/provider-form.xhtml?faces-redirect=true";
    }

    public String saveProvider() {
        try {
            if (providerEditMode) {
                if (providerOriginalEmail == null || providerOriginalEmail.isBlank()) {
                    throw new IllegalStateException("Manjka izvorni email ponudnika.");
                }
                if (!Objects.equals(providerOriginalEmail, providerEmail)) {
                    throw new IllegalArgumentException("Email ponudnika ni mogoče spreminjati.");
                }
                providerService.updateProvider(providerOriginalEmail, providerName, providerAddress);
                message = "Ponudnik je posodobljen.";
            } else {
                providerService.addProvider(new Provider(providerName, providerEmail, providerAddress));
                message = "Ponudnik je dodan.";
            }

            resetProviderForm();
            return "/providers.xhtml?faces-redirect=true";
        } catch (Exception e) {
            message = "Napaka pri shranjevanju ponudnika: " + e.getMessage();
            return null;
        }
    }

    public String cancelProviderForm() {
        resetProviderForm();
        message = "Vnos ponudnika je preklican.";
        return "/providers.xhtml?faces-redirect=true";
    }

    public String requestDeleteProvider(Provider provider) {
        if (provider == null) {
            message = "Izbran ponudnik ne obstaja.";
            return null;
        }

        providerDeleteEmail = provider.getEmail();
        providerDeleteName = provider.getName();
        providerDeleteDialogVisible = true;
        message = "Potrdite brisanje ponudnika.";
        return null;
    }

    public String confirmDeleteProvider() {
        try {
            if (providerDeleteEmail != null && !providerDeleteEmail.isBlank()) {
                providerService.deleteProvider(providerDeleteEmail);
                message = "Ponudnik in pripadajoče polnilnice so izbrisani.";
            }
        } catch (Exception e) {
            message = "Napaka pri brisanju ponudnika: " + e.getMessage();
        } finally {
            cancelDeleteProvider();
        }

        return "/providers.xhtml?faces-redirect=true";
    }

    public String cancelDeleteProvider() {
        providerDeleteDialogVisible = false;
        providerDeleteEmail = null;
        providerDeleteName = null;
        return null;
    }

    public String newStation() {
        resetStationForm();
        stationEditMode = false;
        message = "Vnesite podatke nove polnilnice.";
        return "/station-form.xhtml?faces-redirect=true";
    }

    public String editStation(ChargingStation station) {
        if (station == null) {
            message = "Izbrana polnilnica ne obstaja.";
            return null;
        }

        stationOriginalId = station.getId();
        stationId = station.getId();
        stationLocation = station.getLocation();
        stationConnectorType = station.getConnectorType();
        stationPowerKw = station.getPowerKw();
        stationProviderEmail = station.getProvider() != null ? station.getProvider().getEmail() : null;
        stationActive = station.isActive();
        stationRegion = station.getRegion();
        stationEditMode = true;
        message = "Urejanje polnilnice je pripravljeno.";
        return "/station-form.xhtml?faces-redirect=true";
    }

    public String saveStation() {
        try {
            if (stationEditMode) {
                if (stationOriginalId == null || stationOriginalId.isBlank()) {
                    throw new IllegalStateException("Manjka izvorni ID polnilnice.");
                }
                if (!Objects.equals(stationOriginalId, stationId)) {
                    throw new IllegalArgumentException("ID polnilnice ni mogoče spreminjati.");
                }
                chargingStationService.updateStation(stationOriginalId, stationLocation, stationConnectorType, stationPowerKw, stationProviderEmail, stationActive, stationRegion);
                message = "Polnilnica je posodobljena.";
            } else {
                Provider provider = providerService.getProviderByEmail(stationProviderEmail)
                        .orElseThrow(() -> new IllegalArgumentException("Izbran ponudnik ne obstaja."));
                chargingStationService.addStation(new ChargingStation(stationId, stationLocation, stationConnectorType, stationPowerKw, provider, stationActive, stationRegion));
                message = "Polnilnica je dodana.";
            }

            resetStationForm();
            return "/stations.xhtml?faces-redirect=true";
        } catch (Exception e) {
            message = "Napaka pri shranjevanju polnilnice: " + e.getMessage();
            return null;
        }
    }

    public String cancelStationForm() {
        resetStationForm();
        message = "Vnos polnilnice je preklican.";
        return "/stations.xhtml?faces-redirect=true";
    }

    public String requestDeleteStation(ChargingStation station) {
        if (station == null) {
            message = "Izbrana polnilnica ne obstaja.";
            return null;
        }

        stationDeleteId = station.getId();
        stationDeleteName = station.getLocation();
        stationDeleteDialogVisible = true;
        message = "Potrdite brisanje polnilnice.";
        return null;
    }

    public String confirmDeleteStation() {

        try {
            if (stationDeleteId != null && !stationDeleteId.isBlank()) {
                chargingStationService.deleteStation(stationDeleteId);
                message = "Polnilnica je izbrisana.";
            }
        } catch (Exception e) {
            message = "Napaka pri brisanju polnilnice: " + e.getMessage();
        } finally {
            cancelDeleteStation();
        }

        return "/stations.xhtml?faces-redirect=true";
    }

    public String cancelDeleteStation() {
        stationDeleteDialogVisible = false;
        stationDeleteId = null;
        stationDeleteName = null;
        return null;
    }

    private void resetUserForm() {
        userName = null;
        userEmail = null;
        userCarType = null;
        userBalance = 0;
        userEditMode = false;
        userOriginalEmail = null;
    }

    private void resetProviderForm() {
        providerName = null;
        providerEmail = null;
        providerAddress = null;
        providerEditMode = false;
        providerOriginalEmail = null;
    }

    private void resetStationForm() {
        stationId = null;
        stationLocation = null;
        stationConnectorType = null;
        stationPowerKw = 0;
        stationProviderEmail = null;
        stationActive = true;
        stationRegion = null;
        stationEditMode = false;
        stationOriginalId = null;
    }
}

