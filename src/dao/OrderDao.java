package dao;

import entities.Order;
import entities.Status;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends DAO<Order> {
    List<Order> getOrdersByUser(long userId) throws SQLException;

    Order getOrdersByUserAndStatus(long userId, Status status) throws SQLException;
}
