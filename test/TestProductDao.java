import DAO.ProductDao;
import DAO.impl.ProductDaoImpl;
import db.ConnectionDB;
import entities.Product;
import entities.ProductCategory;
import entities.ProductSubcategory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;


public class TestProductDao {

    private ProductDao productDao = ProductDaoImpl.getInstance();

    @Test
    public void fullTest() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        connection.setAutoCommit(true);
        int before = productDao.getAll().size();
        Product product = new Product("куртка", 15.2, 0, ProductCategory.CLOTHES, ProductSubcategory.GIRLS, "", "", "");
        productDao.save(product);
        int after = productDao.getAll().size();
        Assert.assertNotSame(before, after);

        product.setPrice(14.5);
        productDao.update(product);
        Product newProduct = productDao.get(product.getId());
        Assert.assertEquals(14.5, newProduct.getPrice(), 0.00);

        newProduct = productDao.getProductByName("куртка");
        Assert.assertEquals("куртка", newProduct.getName());

        int sizeListBefore = productDao.getProductsByCategory(ProductCategory.CLOTHES).size();
        int sizeBefore = productDao.getProductsBySubcategory("girls").size();
        productDao.save(newProduct);
        int sizeListAfter = productDao.getProductsByCategory(ProductCategory.CLOTHES).size();
        int sizeAfter = productDao.getProductsBySubcategory("girls").size();
        Assert.assertNotSame(sizeListBefore, sizeListAfter);
        Assert.assertNotSame(sizeBefore, sizeAfter);

        int size = productDao.getProductsByDiscount(0.2).size();
        Assert.assertNotSame(0, size);

        productDao.delete(newProduct);

        productDao.delete(product);
        after = productDao.getAll().size();
        Assert.assertEquals(before, after);

    }
}
