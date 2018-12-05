package services;

import entities.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {
    User save(User user);

    User get(Serializable id);

    int update(User user);

    int delete(User user);

    List<User> getAll();

    int updatePassword(User user);

    int updateEmail(User user);

    int setDiscount(User user);

    int setStatus(User user);

    User getUserByEmail(String email);
}
