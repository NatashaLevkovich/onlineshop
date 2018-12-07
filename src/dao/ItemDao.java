package dao;

import entities.Item;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface ItemDao extends DAO<Item> {
    List<Item> getItemsByOrder(Serializable orderId) throws SQLException;
}
