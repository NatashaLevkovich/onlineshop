package services;

import entities.Order;
import entities.Status;

import java.io.Serializable;
import java.util.List;

public interface OrderService {

    Order save(long userId, long productId, int productSize, int quantity);

    Order get(Serializable id);

    Order update(Order order);

    int delete(Order order);

    List<Order> getAll();

    List<Order> getOrdersByUser(long userId);

    Order getOrdersByUserAndStatus(long userId, Status status);

}
