package DAO;

import entities.ProductSize;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ProductSizeDao {

    Map<Integer, Integer> getSizeAndQuantityByProduct(Serializable productId) throws SQLException;

    ProductSize getSizeByProductAndSize(Serializable productId, int size) throws SQLException;

    int save(ProductSize t) throws SQLException;

    ProductSize get(Serializable id) throws SQLException;

    int update(ProductSize t) throws SQLException;

    int delete(Serializable productId) throws SQLException;

    List<ProductSize> getAll() throws SQLException;
}
