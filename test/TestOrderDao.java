import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import db.ConnectionDB;
import entities.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

public class TestOrderDao {

    private OrderDao orderDao = OrderDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        connection.setAutoCommit(true);
        int before = orderDao.getAll().size();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 10, 21);
        Order order = new Order(5L, Status.NEW, calendar);
        orderDao.save(order);
        int after = orderDao.getAll().size();
        Assert.assertNotSame(before, after);

        order.setTotalPrice(1400);
        orderDao.update(order);
        Order newOrder = orderDao.get(order.getId());
        Assert.assertEquals(1400.00, newOrder.getTotalPrice(), 0.00);



        int sizeListBefore = orderDao.getOrdersByUser(1).size();
        orderDao.save(newOrder);
        int sizeListAfter = orderDao.getOrdersByUser(newOrder.getId()).size();
        Assert.assertNotSame(sizeListBefore, sizeListAfter);


        orderDao.delete(newOrder);

        orderDao.delete(order);
        after = orderDao.getAll().size();
        Assert.assertEquals(before, after);

    }
}
