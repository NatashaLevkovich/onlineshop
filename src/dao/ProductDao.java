package dao;

import entities.Product;
import entities.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends DAO<Product> {
    Product getProductByName(String name) throws SQLException;

    List<Product> getProductsByCategory(ProductCategory category) throws SQLException;

    List<Product> getProductsBySubcategory(String subcategory) throws SQLException;

    List<Product> getProductsByDiscount(double discount) throws SQLException;
}
