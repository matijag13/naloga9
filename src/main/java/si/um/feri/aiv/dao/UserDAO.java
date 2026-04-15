package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.dao.interfaces.UserDAOInterface;
import si.um.feri.aiv.vao.User;

import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAO implements UserDAOInterface {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insertUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.name", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.ofNullable(em.find(User.class, email));
    }

    @Override
    public void updateUser(String email, String name, double balance, String carType) {
        User user = em.find(User.class, email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.setName(name);
        user.setBalance(balance);
        user.setCarType(carType);
    }

    @Override
    public void updateUserBalance(String email, double newBalance) {
        User user = em.find(User.class, email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.setBalance(newBalance);
    }

    @Override
    public void deleteUser(String email) {
        User user = em.find(User.class, email);
        if (user != null) {
            em.remove(user);
        }
    }
}

