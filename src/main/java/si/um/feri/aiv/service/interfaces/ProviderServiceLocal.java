package si.um.feri.aiv.service.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Provider;

import java.util.List;
import java.util.Optional;

@Local
public interface ProviderServiceLocal {
    void addProvider(Provider provider);
    void updateProvider(String originalEmail, String name, String address);
    List<Provider> getAllProviders();
    Optional<Provider> getProviderByEmail(String email);
    void updateProviderAddress(String email, String newAddress);
    void deleteProvider(String email);
}

