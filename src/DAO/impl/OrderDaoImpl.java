package DAO.impl;

import DAO.OrderDao;
import entities.Order;
import entities.Status;


import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OrderDaoImpl extends AbstractDao implements OrderDao {

    private static final String insertOrderQuery = "INSERT INTO `ORDER` (USER_ID, TOTAL_PRICE, STATUS, ORDER_DATE) VALUES (?, ?, ?, ?)";
    private static final String selectOrderQuery = "SELECT * FROM `ORDER` WHERE ID = ?";
    private static final String updateOrderQuery = "UPDATE `ORDER` SET TOTAL_PRICE = ?, STATUS = ? WHERE ID = ?";
    private static final String deleteOrderQuery = "DELETE FROM `ORDER` WHERE ID = ?;";
    private static final String getOrdersByUser = "SELECT * FROM `ORDER` WHERE USER_ID = ?";
    private static final String getOrdersByUserAndStatus = "SELECT * FROM `ORDER` WHERE USER_ID = ? AND STATUS = ?";
    private static final String selectAllOrderQuery = "SELECT * FROM `ORDER`";

    private OrderDaoImpl() {
    }

    private static volatile OrderDao INSTANCE = null;

    public static OrderDao getInstance() {
        OrderDao orderDao = INSTANCE;
        if (orderDao == null) {
            synchronized (OrderDaoImpl.class) {
                orderDao = INSTANCE;
                if (orderDao == null) {
                    INSTANCE = orderDao = new OrderDaoImpl();
                }
            }
        }
        return orderDao;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        PreparedStatement ps = prepareStatement(selectAllOrderQuery);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        return getList(resultSet);
    }

    @Override
    public List<Order> getOrdersByUser(long userId) throws SQLException {
        PreparedStatement ps = prepareStatement(getOrdersByUser);
        ps.setLong(1, (long) userId);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        return getList(resultSet);
    }

    public Order getOrdersByUserAndStatus(long userId, Status status) throws SQLException {
        PreparedStatement ps = prepareStatement(getOrdersByUserAndStatus);
        ps.setLong(1, (long) userId);
        ps.setString(2, status.toString());
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        List<Order> list = getList(resultSet);
        Order order = new Order();
        if (list.size() > 0) {
            order = list.get(0);
        }

        return order;
    }

    @Override
    public int save(Order order) throws SQLException {
        PreparedStatement ps = prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, order.getUserId());
        ps.setDouble(2, order.getTotalPrice());
        ps.setString(3, order.getStatus().toString());
        ps.setDate(4, Date.valueOf(order.getOrderDateToString()));
        int result = ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            order.setId(resultSet.getLong(1));
        }
        return result;
    }

    @Override
    public Order get(Serializable id) throws SQLException {
        PreparedStatement ps = prepareStatement(selectOrderQuery);
        ps.setLong(1, (long) id);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        List<Order> list = getList(resultSet);
        Order order = new Order();
        if (list.size() > 0) {
            order = list.get(0);
        }

        return order;
    }

    @Override
    public int update(Order order) throws SQLException {
        PreparedStatement ps = prepareStatement(updateOrderQuery);
        ps.setDouble(1, order.getTotalPrice());
        ps.setString(2, order.getStatus().toString());
        ps.setLong(3, order.getId());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Order order) throws SQLException {
        PreparedStatement ps = prepareStatement(deleteOrderQuery);
        ps.setLong(1, order.getId());
        return ps.executeUpdate();
    }


    private List<Order> getList(ResultSet resultSet) throws SQLException {
        List<Order> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong(1));
            order.setUserId(resultSet.getLong(2));
            order.setTotalPrice(resultSet.getDouble(3));
            order.setStatus(Status.valueOf(resultSet.getString(4)));
            String s = resultSet.getString(5);
            if (s != null) {
                String[] strings = s.split("-");
                if (strings.length == 3) {
                    calendar.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                    order.setOrderDate(calendar);
                } else {
                    order.setOrderDate(calendar);
                }
            } else {
                order.setOrderDate(calendar);
            }
            list.add(order);
        }

        return list;
    }

}
