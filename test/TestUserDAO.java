import DAO.UserDao;
import DAO.impl.UserDaoImpl;
import db.ConnectionDB;
import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestUserDAO {

    private UserDao userDAO = UserDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        connection.setAutoCommit(true);
        int before = userDAO.getAll().size();
        User user = new User("email", "123456");
        userDAO.save(user);
        int after = userDAO.getAll().size();
        Assert.assertNotSame(before, after);


        user.setPhoneNumber("987456");
        userDAO.update(user);
        User newUser = userDAO.get(user.getId());
        Assert.assertEquals("987456", newUser.getPhoneNumber());

        user.setPassword("123456789");
        userDAO.updatePassword(user);
        newUser = userDAO.get(user.getId());
        Assert.assertEquals("123456789", newUser.getPassword());

        user.setDiscount(0.05);
        userDAO.setDiscount(user);
        newUser = userDAO.get(user.getId());
        Assert.assertEquals((0.05), newUser.getDiscount(), 0.00);

        user.setEmail("mail");
        userDAO.updateEmail(user);
        newUser = userDAO.get(user.getId());
        Assert.assertEquals("mail", newUser.getEmail());

        user.setStatus("deleted");
        userDAO.setStatus(user);
        newUser = userDAO.get(user.getId());
        Assert.assertEquals("deleted", newUser.getStatus());

        newUser = userDAO.getUserByEmailAndPassword("mail");
        Assert.assertEquals("mail", newUser.getEmail());
        Assert.assertEquals("123456789", newUser.getPassword());

        userDAO.delete(user);
        after = userDAO.getAll().size();
        Assert.assertEquals(before, after);

    }


}
