import DAO.ItemDao;
import DAO.impl.ItemDaoImpl;
import db.ConnectionDB;
import entities.Item;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestItemDao {

    private ItemDao itemDao = ItemDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        connection.setAutoCommit(true);
        int before = itemDao.getAll().size();
        Item item = new Item(1L, 1, 1, 1, 1);
        itemDao.save(item);
        int after = itemDao.getAll().size();
        Assert.assertNotSame(before, after);

        item.setDiscount(0.05);
        itemDao.update(item);
        Item newItem = itemDao.get(item.getId());
        Assert.assertEquals(0.05, newItem.getDiscount(), 0.00);

        int sizeListBefore = itemDao.getItemsByOrder(1L).size();
        itemDao.save(newItem);
        int sizeListAfter = itemDao.getItemsByOrder(newItem.getId()).size();
        Assert.assertNotSame(sizeListBefore, sizeListAfter);

        itemDao.delete(newItem);

        itemDao.delete(item);
        after = itemDao.getAll().size();
        Assert.assertEquals(before, after);

    }
}
