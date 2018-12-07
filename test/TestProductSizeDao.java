import dao.ProductSizeDao;
import dao.impl.ProductSizeDaoImpl;
import db.ConnectionDB;
import entities.ProductSize;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class TestProductSizeDao {

    private ProductSizeDao productSizeDao = ProductSizeDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        connection.setAutoCommit(true);
        int before = productSizeDao.getAll().size();
        ProductSize productSize = new ProductSize(1, 52, 5);
        productSizeDao.save(productSize);
        int after = productSizeDao.getAll().size();
        Assert.assertNotSame(before, after);

        productSize.setSize(60);
        productSizeDao.update(productSize);
        ProductSize newProductSize = productSizeDao.get(productSize.getId());
        Assert.assertEquals(60, newProductSize.getSize());

        Map<Integer, Integer> map = productSizeDao.getSizeAndQuantityByProduct(1L);
        int key = 0;
        int value = 0;
        for (Map.Entry<Integer, Integer> m : map.entrySet()){
            key = m.getKey();
            value = m.getValue();
        }
        Assert.assertEquals(key, productSize.getSize());
        Assert.assertEquals(value, productSize.getQuantity());

        productSizeDao.delete(1L);
        after = productSizeDao.getAll().size();
        Assert.assertEquals(before, after);
    }
}
