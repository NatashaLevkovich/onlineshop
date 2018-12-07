package dao.impl;

import dao.ProductSizeDao;
import entities.ProductSize;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSizeDaoImpl extends AbstractDao implements ProductSizeDao {
    private static final String insertProductSizeQuery = "INSERT INTO PRODUCT_SIZE (PRODUCT_ID, SIZE, QUANTITY) VALUES (?, ?, ?)";
    private static final String selectProductSize = "SELECT * FROM PRODUCT_SIZE WHERE ID = ?";
    private static final String updateProductSizeQuery = "UPDATE PRODUCT_SIZE SET SIZE = ?, QUANTITY = ? WHERE ID = ?";
    private static final String deleteProductSizeQuery = "DELETE FROM PRODUCT_SIZE WHERE PRODUCT_ID = ?";
    private static final String getProductSizeByProduct = "SELECT * FROM PRODUCT_SIZE WHERE PRODUCT_ID = ?";
    private static final String getProductSizeByProductAndSize = "SELECT * FROM PRODUCT_SIZE WHERE PRODUCT_ID = ? AND SIZE = ?";
    private static final String selectAllProductSizeQuery = "SELECT * FROM PRODUCT_SIZE";

    private ProductSizeDaoImpl() {
    }

    private static volatile ProductSizeDao INSTANCE = null;

    public static ProductSizeDao getInstance() {
        ProductSizeDao productSizeDao = INSTANCE;
        if (productSizeDao == null) {
            synchronized (ProductSizeDaoImpl.class) {
                productSizeDao = INSTANCE;
                if (productSizeDao == null) {
                    INSTANCE = productSizeDao = new ProductSizeDaoImpl();
                }
            }
        }
        return productSizeDao;
    }

    @Override
    public Map<Integer, Integer> getSizeAndQuantityByProduct(Serializable productId) throws SQLException {
        PreparedStatement ps = prepareStatement(getProductSizeByProduct);
        ps.setLong(1, (long) productId);
        ps.executeQuery();
        Map<Integer, Integer> map = new HashMap<>();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            map.put(rs.getInt(3), rs.getInt(4));
        }
        return map;
    }

    @Override
    public ProductSize getSizeByProductAndSize(Serializable productId, int size) throws SQLException {
        PreparedStatement ps = prepareStatement(getProductSizeByProductAndSize);
        ps.setLong(1, (long) productId);
        ps.setInt(2, size);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        ProductSize productSize = new ProductSize();
        if(resultSet.next()) {
            productSize = new ProductSize(resultSet.getLong(1), resultSet.getLong(2), resultSet.getInt(3),
                    resultSet.getInt(4));
        }
        return productSize;
    }


    public int save(ProductSize productSize) throws SQLException {
        PreparedStatement ps = prepareStatement(insertProductSizeQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setLong(1, productSize.getProductId());
        ps.setInt(2, productSize.getSize());
        ps.setInt(3, productSize.getQuantity());
        int result = ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        if (resultSet.next()) {
            productSize.setId(resultSet.getLong(1));
        }
        return result;

    }


    public ProductSize get(Serializable productId) throws SQLException {
        PreparedStatement ps = prepareStatement(selectProductSize);
        ps.setLong(1, (long) productId);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        ProductSize productSize = new ProductSize();
        if (resultSet.next()) {
            productSize = new ProductSize(resultSet.getLong(1), resultSet.getLong(2), resultSet.getInt(3),
                    resultSet.getInt(4));
        }
        return productSize;
    }


    public int update(ProductSize productSize) throws SQLException {
        PreparedStatement ps = prepareStatement(updateProductSizeQuery);
        ps.setInt(1, productSize.getSize());
        ps.setInt(2, productSize.getQuantity());
        ps.setLong(3, productSize.getId());
        return ps.executeUpdate();
    }


    public int delete(Serializable productId) throws SQLException {
        PreparedStatement ps = prepareStatement(deleteProductSizeQuery);
        ps.setLong(1, (long) productId);
        return ps.executeUpdate();
    }


    public List<ProductSize> getAll() throws SQLException {
        PreparedStatement ps = prepareStatement(selectAllProductSizeQuery);
        ps.executeQuery();
        List<ProductSize> list = new ArrayList<>();
        ResultSet rs = ps.getResultSet();
        while (rs.next()) {
            ProductSize productSize = new ProductSize(rs.getLong(1), rs.getLong(2), rs.getInt(3),
                    rs.getInt(4));
            list.add(productSize);
        }
        return list;
    }

}

