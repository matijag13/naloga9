package si.um.feri.aiv.service.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.User;

import java.util.List;
import java.util.Optional;

@Local
public interface UserServiceLocal {
    void addUser(User user);
    void updateUser(String originalEmail, String name, double balance, String carType);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    void decreaseBalance(String email, double amount);
    void deleteUser(String email);
}

