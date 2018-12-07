package dao.impl;

import dao.UserDao;
import entities.User;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final String insertUserQuery = "INSERT INTO USER (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, DISCOUNT, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectUserQuery = "SELECT * FROM USER WHERE ID = ?";
    private static final String selectAllUserQuery = "SELECT * FROM USER";
    private static final String updateUserQuery = "UPDATE USER SET FIRST_NAME = ?, LAST_NAME = ?, ADDRESS = ?, PHONE_NUMBER = ? WHERE ID = ?";
    private static final String deleteUserQuery = "DELETE FROM USER WHERE ID = ?";
    private static final String getUserByEmail = "SELECT * FROM USER WHERE EMAIL = ?";
    private static final String updateUserPasswordQuery = "UPDATE USER SET PASSWORD = ? WHERE ID = ?";
    private static final String updateUserEmailQuery = "UPDATE USER SET EMAIL = ? WHERE ID = ?";
    private static final String setDiscountQuery = "UPDATE USER SET DISCOUNT = ? WHERE ID = ?";
    private static final String setStatusQuery = "UPDATE USER SET STATUS = ? WHERE ID = ?";


    private UserDaoImpl() {
    }

    private static volatile UserDao INSTANCE = null;

    public static UserDao getInstance() {
        UserDao userDAO = INSTANCE;
        if (userDAO == null) {
            synchronized (UserDaoImpl.class) {
                userDAO = INSTANCE;
                if (userDAO == null) {
                    INSTANCE = userDAO = new UserDaoImpl();
                }
            }
        }
        return userDAO;
    }

    public int save(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(insertUserQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getAddress());
        ps.setString(6, user.getPhoneNumber());
        ps.setDouble(7, user.getDiscount());
        ps.setString(8, user.getStatus());
        int result = ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            user.setId(resultSet.getLong(1));
        }
        return result;
    }


    public int update(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(updateUserQuery);
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getAddress());
        ps.setString(4, user.getPhoneNumber());
        ps.setLong(5, user.getId());
        return ps.executeUpdate();
    }


    public int delete(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(deleteUserQuery);
        ps.setLong(1, user.getId());
        return ps.executeUpdate();
    }

    public User get(Serializable id) throws SQLException {
        PreparedStatement ps = prepareStatement(selectUserQuery);
        ps.setLong(1, (long) id);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        User user = new User();
        if (resultSet.next()) {
            user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getDouble(8), resultSet.getString(9));
        }
        return user;
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement ps = prepareStatement(selectAllUserQuery);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getDouble(8), resultSet.getString(9));
            list.add(user);
        }

        return list;

    }

    @Override
    public User getUserByEmailAndPassword(String email) throws SQLException {
        PreparedStatement ps = prepareStatement(getUserByEmail);
        ps.setString(1, email);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        User user = new User();
        if (resultSet.next()) {
            user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getDouble(8), resultSet.getString(9));

        }
        return user;
    }

    @Override
    public int updatePassword(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(updateUserPasswordQuery);
        ps.setString(1, user.getPassword());
        ps.setLong(2, user.getId());
        return ps.executeUpdate();
    }

    @Override
    public int updateEmail(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(updateUserEmailQuery);
        ps.setString(1, user.getEmail());
        ps.setLong(2, user.getId());
        return ps.executeUpdate();
    }

    @Override
    public int setDiscount(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(setDiscountQuery);
        ps.setDouble(1, user.getDiscount());
        ps.setLong(2, user.getId());
        return ps.executeUpdate();
    }

    @Override
    public int setStatus(User user) throws SQLException {
        PreparedStatement ps = prepareStatement(setStatusQuery);
        ps.setString(1, user.getStatus());
        ps.setLong(2, user.getId());
        return ps.executeUpdate();
    }
}
