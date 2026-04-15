package si.um.feri.aiv.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.aiv.dao.interfaces.UserDAOInterface;
import si.um.feri.aiv.service.interfaces.UserServiceLocal;
import si.um.feri.aiv.vao.User;

import java.util.List;
import java.util.Optional;

@Stateless
public class UserService implements UserServiceLocal {

    @EJB
    private UserDAOInterface userDAO;

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("User name is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("User email is required");
        }
        if (user.getCarType() == null || user.getCarType().isBlank()) {
            throw new IllegalArgumentException("User car type is required");
        }
        if (user.getBalance() < 0) {
            throw new IllegalArgumentException("User balance cannot be negative");
        }
        if (userDAO.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        userDAO.insertUser(user);
    }

    @Override
    public void updateUser(String originalEmail, String name, double balance, String carType) {
        if (originalEmail == null || originalEmail.isBlank()) {
            throw new IllegalArgumentException("Original email is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name is required");
        }
        if (carType == null || carType.isBlank()) {
            throw new IllegalArgumentException("User car type is required");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("User balance cannot be negative");
        }

        userDAO.updateUser(originalEmail, name, balance, carType);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void decreaseBalance(String email, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        User user = userDAO.getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getBalance() < amount) {
            throw new IllegalStateException("Insufficient user balance");
        }

        userDAO.updateUserBalance(email, user.getBalance() - amount);
    }

    @Override
    public void deleteUser(String email) {
        userDAO.deleteUser(email);
    }
}

