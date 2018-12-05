package DAO.impl;

import DAO.ProductDao;
import entities.Product;
import entities.ProductCategory;
import entities.ProductSubcategory;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends AbstractDao implements ProductDao {

    private static final String insertProductQuery = "INSERT INTO PRODUCT (NAME, PRICE, DISCOUNT, CATEGORY, SUBCATEGORY, IMAGE, MANUFACTURER, MATERIAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String selectProductQuery = "SELECT * FROM PRODUCT WHERE ID = ?";
    private static final String updateProductQuery = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, DISCOUNT = ?, CATEGORY = ?, SUBCATEGORY = ?, IMAGE = ?, MANUFACTURER = ?, MATERIAL = ? WHERE ID = ?";
    private static final String deleteProductQuery = "DELETE FROM PRODUCT WHERE ID = ?;";
    private static final String getProductByNameQuery = "SELECT * FROM PRODUCT WHERE NAME = ?";
    private static final String getProductByCategoryQuery = "SELECT * FROM PRODUCT WHERE CATEGORY = ?";
    private static final String getProductBySubcategoryQuery = "SELECT * FROM PRODUCT WHERE SUBCATEGORY = ?";
    private static final String getProductByDiscount = "SELECT * FROM PRODUCT WHERE DISCOUNT > ?";
    private static final String getAllProductQuery = "SELECT * FROM PRODUCT";

    private ProductDaoImpl() {
    }

    private static volatile ProductDao INSTANCE = null;

    public static ProductDao getInstance() {
        ProductDao productDao = INSTANCE;
        if (productDao == null) {
            synchronized (ProductDaoImpl.class) {
                productDao = INSTANCE;
                if (productDao == null) {
                    INSTANCE = productDao = new ProductDaoImpl();
                }
            }
        }
        return productDao;
    }

    @Override
    public List<Product> getProductsByDiscount(double discount) throws SQLException {
        PreparedStatement ps = prepareStatement(getProductByDiscount);
        ps.setDouble(1, discount);
        List<Product> products = new ArrayList<>();
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), ProductCategory.valueOf(resultSet.getString(5)),
                    ProductSubcategory.valueOf(resultSet.getString(6)), resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9));
            products.add(product);
        }

        return products;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        PreparedStatement ps = prepareStatement(getAllProductQuery);
        List<Product> products = new ArrayList<>();
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), ProductCategory.valueOf(resultSet.getString(5)),
                    ProductSubcategory.valueOf(resultSet.getString(6)), resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9));
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProductByName(String name) throws SQLException {
        PreparedStatement ps = prepareStatement(getProductByNameQuery);
        ps.setString(1, name);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        resultSet.next();
        Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3),
                resultSet.getDouble(4), ProductCategory.valueOf(resultSet.getString(5)),
                ProductSubcategory.valueOf(resultSet.getString(6)), resultSet.getString(7), resultSet.getString(8),
                resultSet.getString(9));

        return product;
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory category) throws SQLException {
        PreparedStatement ps = prepareStatement(getProductByCategoryQuery);
        return getList(ps, category);
    }

    @Override
    public List<Product> getProductsBySubcategory(String subcategory) throws SQLException {
        ProductSubcategory category = ProductSubcategory.getSubcategory(subcategory);
        PreparedStatement ps = prepareStatement(getProductBySubcategoryQuery);
        return getList(ps, category);
    }

    @Override
    public int save(Product product) throws SQLException {
        PreparedStatement ps = prepareStatement(insertProductQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setDouble(3, product.getDiscount());
        ps.setString(4, product.getCategory().toString());
        ps.setString(5, product.getSubcategory().toString());
        ps.setString(6, product.getImage());
        ps.setString(7, product.getManufacturer());
        ps.setString(8, product.getMaterial());
        int result = ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        resultSet.next();
        product.setId(resultSet.getLong(1));

        return result;
    }

    @Override
    public Product get(Serializable id) throws SQLException {
        PreparedStatement ps = prepareStatement(selectProductQuery);
        ps.setLong(1, (long) id);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        Product product = new Product();
        if (resultSet.next()) {
            product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), ProductCategory.valueOf(resultSet.getString(5)),
                    ProductSubcategory.valueOf(resultSet.getString(6)), resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9));
        }
        return product;
    }

    @Override
    public int update(Product product) throws SQLException {
        PreparedStatement ps = prepareStatement(updateProductQuery);
        ps.setString(1, product.getName());
        ps.setDouble(2, product.getPrice());
        ps.setDouble(3, product.getDiscount());
        ps.setString(4, product.getCategory().toString());
        ps.setString(5, product.getSubcategory().toString());
        ps.setString(6, product.getImage());
        ps.setString(7, product.getManufacturer());
        ps.setString(8, product.getMaterial());
        ps.setLong(9, product.getId());
        return ps.executeUpdate();
    }

    @Override
    public int delete(Product product) throws SQLException {
        PreparedStatement ps = prepareStatement(deleteProductQuery);
        ps.setLong(1, product.getId());
        return ps.executeUpdate();
    }

    private List<Product> getList(PreparedStatement ps, Object o) throws SQLException {
        List<Product> products = new ArrayList<>();
        ps.setString(1, o.toString());
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3),
                    resultSet.getDouble(4), ProductCategory.valueOf(resultSet.getString(5)),
                    ProductSubcategory.valueOf(resultSet.getString(6)), resultSet.getString(7), resultSet.getString(8),
                    resultSet.getString(9));
            products.add(product);
        }
        return products;
    }
}
