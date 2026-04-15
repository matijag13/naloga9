package si.um.feri.aiv.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Provider;
import java.util.List;
import java.util.Optional;

@Local
public interface ProviderDAOInterface {
    void insertProvider(Provider provider);
    List<Provider> getAllProviders();
    Optional<Provider> getProviderByEmail(String email);
    void updateProvider(String email, String name, String address);
    void updateProvider(String email, String newAddress);
    void deleteProvider(String email);
}

