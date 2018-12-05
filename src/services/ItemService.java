package services;

import entities.Item;
import entities.Order;

import java.io.Serializable;
import java.util.List;

public interface ItemService {
    int save(Order order, long productId, int productSize, int quantity);

    Item get(Serializable id);

    int update(Item item, Order order, int quantity);

    int delete(Item item);

    List<Item> getAll();

    List<Item> getItemsByOrder(Serializable orderId);
}
