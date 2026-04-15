package si.um.feri.aiv.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.aiv.dao.interfaces.ProviderDAOInterface;
import si.um.feri.aiv.service.interfaces.ProviderServiceLocal;
import si.um.feri.aiv.vao.Provider;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProviderService implements ProviderServiceLocal {

    @EJB
    private ProviderDAOInterface providerDAO;

    @Override
    public void addProvider(Provider provider) throws IllegalArgumentException {
        if (provider == null) {
            throw new IllegalArgumentException("Provider cannot be null");
        }
        if (provider.getName() == null || provider.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (provider.getEmail() == null || provider.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (provider.getAddress() == null || provider.getAddress().isBlank()) {
            throw new IllegalArgumentException("Address is required");
        }
        if (providerDAO.getProviderByEmail(provider.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Provider with this email already exists");
        }
        providerDAO.insertProvider(provider);
    }

    @Override
    public void updateProvider(String originalEmail, String name, String address) {
        if (originalEmail == null || originalEmail.isBlank()) {
            throw new IllegalArgumentException("Original email is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address is required");
        }

        providerDAO.updateProvider(originalEmail, name, address);
    }

    @Override
    public List<Provider> getAllProviders() {
        return providerDAO.getAllProviders();
    }

    @Override
    public Optional<Provider> getProviderByEmail(String email) {
        if (email == null) return Optional.empty();
        return providerDAO.getProviderByEmail(email);
    }

    @Override
    public void updateProviderAddress(String email, String newAddress) {
        if (newAddress == null || newAddress.isEmpty()) {
            throw new IllegalArgumentException("New address cannot be empty");
        }
        providerDAO.updateProvider(email, newAddress);
    }

    @Override
    public void deleteProvider(String email) {
        if (email == null || email.isBlank()) {
            return;
        }

        providerDAO.deleteProvider(email);
    }
}

