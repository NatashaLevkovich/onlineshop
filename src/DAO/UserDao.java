package DAO;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends DAO<User> {
    int updatePassword(User user) throws SQLException;

    int updateEmail(User user) throws SQLException;

    int setDiscount(User user) throws SQLException;

    int setStatus(User user) throws SQLException;

    User getUserByEmailAndPassword(String email) throws SQLException;
}
