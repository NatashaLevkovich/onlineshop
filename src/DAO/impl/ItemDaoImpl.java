package DAO.impl;

import DAO.ItemDao;
import entities.Item;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl extends AbstractDao implements ItemDao {

    private static final String insertItemQuery = "INSERT INTO ITEM (PRODUCT_ID, PRODUCT_SIZE, ORDER_ID, QUANTITY, DISCOUNT) VALUES (?, ?, ?, ?, ?)";
    private static final String selectItemQuery = "SELECT * FROM ITEM WHERE ID = ?";
    private static final String updateItemQuery = "UPDATE ITEM SET PRODUCT_SIZE = ?, QUANTITY = ?, DISCOUNT = ? WHERE ID = ?";
    private static final String deleteItemQuery = "DELETE FROM ITEM WHERE ID = ?";
    private static final String getItemByOrder = "SELECT * FROM ITEM WHERE ORDER_ID = ?";
    private static final String selectAllItemQuery = "SELECT * FROM ITEM";

    private ItemDaoImpl() {
    }

    private static volatile ItemDao INSTANCE = null;

    public static ItemDao getInstance() {
        ItemDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new ItemDaoImpl();
                }
            }
        }
        return itemDao;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        PreparedStatement ps = prepareStatement(selectAllItemQuery);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        List<Item> list = new ArrayList<>();
        while (resultSet.next()) {
            Item item = new Item(resultSet.getLong(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getLong(4),
                    resultSet.getInt(5), resultSet.getDouble(6));
            list.add(item);
        }

        return list;
    }

    @Override
    public List<Item> getItemsByOrder(Serializable orderId) throws SQLException {
        PreparedStatement ps = prepareStatement(getItemByOrder);
        List<Item> list = new ArrayList<>();
        ps.setLong(1, (long) orderId);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            Item item = new Item(resultSet.getLong(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getLong(4),
                    resultSet.getInt(5), resultSet.getDouble(6));
            list.add(item);
        }

        return list;
    }

    @Override
    public int save(Item item) throws SQLException {
        PreparedStatement ps = prepareStatement(insertItemQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, item.getProductId());
        ps.setInt(2, item.getProductSize());
        ps.setLong(3, item.getOrderId());
        ps.setInt(4, item.getQuantity());
        ps.setDouble(5, item.getDiscount());
        int result = ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            item.setId(resultSet.getLong(1));
        }
        return result;
    }

    @Override
    public Item get(Serializable id) throws SQLException {
        PreparedStatement ps = prepareStatement(selectItemQuery);
        ps.setLong(1, (long) id);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        Item item = new Item();
        if (resultSet.next()) {
            item = new Item(resultSet.getLong(1), resultSet.getLong(2), resultSet.getInt(3), resultSet.getLong(4),
                    resultSet.getInt(5), resultSet.getDouble(6));
        }
        return item;
    }

    @Override
    public int update(Item item) throws SQLException {
        PreparedStatement ps = prepareStatement(updateItemQuery);
        ps.setInt(1, item.getProductSize());
        ps.setInt(2, item.getQuantity());
        ps.setDouble(3, item.getDiscount());
        ps.setLong(4, item.getId());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Item item) throws SQLException {
        PreparedStatement ps = prepareStatement(deleteItemQuery);
        ps.setLong(1, item.getId());
        return ps.executeUpdate();
    }


}
