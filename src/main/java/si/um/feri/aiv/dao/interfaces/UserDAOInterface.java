package si.um.feri.aiv.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.User;

import java.util.List;
import java.util.Optional;

@Local
public interface UserDAOInterface {
    void insertUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    void updateUser(String email, String name, double balance, String carType);
    void updateUserBalance(String email, double newBalance);
    void deleteUser(String email);
}

