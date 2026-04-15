package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.dao.interfaces.ProviderDAOInterface;
import si.um.feri.aiv.vao.Provider;
import java.util.List;
import java.util.Optional;

@Stateless
public class ProviderDAO implements ProviderDAOInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertProvider(Provider provider) {
        em.persist(provider);
    }

    @Override
    public List<Provider> getAllProviders() {
        return em.createQuery("SELECT p FROM Provider p ORDER BY p.name", Provider.class)
                .getResultList();
    }

    @Override
    public Optional<Provider> getProviderByEmail(String email) {
        return Optional.ofNullable(em.find(Provider.class, email));
    }

    @Override
    public void updateProvider(String email, String name, String address) {
        Provider provider = em.find(Provider.class, email);
        if (provider == null) {
            throw new IllegalArgumentException("Provider not found");
        }

        provider.setName(name);
        provider.setAddress(address);
    }

    @Override
    public void updateProvider(String email, String newAddress) {
        Provider provider = em.find(Provider.class, email);
        if (provider == null) {
            throw new IllegalArgumentException("Provider not found");
        }

        provider.setAddress(newAddress);
    }

    @Override
    public void deleteProvider(String email) {
        Provider provider = em.find(Provider.class, email);
        if (provider != null) {
            em.remove(provider);
        }
    }
}

