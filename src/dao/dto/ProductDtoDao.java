package dao.dto;

import dao.impl.AbstractDao;
import entities.dto.ProductDto;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoDao extends AbstractDao {
    private static final String selectProductDtoQuery = "SELECT PRODUCT.NAME, PRODUCT.PRICE, PRODUCT.IMAGE, PRODUCT.MANUFACTURER, PRODUCT.MATERIAL, ITEM.PRODUCT_SIZE, ITEM.QUANTITY, ITEM.DISCOUNT, ITEM.ID FROM ITEM  JOIN PRODUCT ON PRODUCT.ID = PRODUCT_ID WHERE ORDER_ID = ?";

    private ProductDtoDao() {
    }

    private static volatile ProductDtoDao INSTANCE = null;

    public static ProductDtoDao getInstance() {
        ProductDtoDao productDtoDao = INSTANCE;
        if (productDtoDao == null) {
            synchronized (ProductDtoDao.class) {
                productDtoDao = INSTANCE;
                if (productDtoDao == null) {
                    INSTANCE = productDtoDao = new ProductDtoDao();
                }
            }
        }
        return productDtoDao;
    }

    public List<ProductDto> get(Serializable id) throws SQLException {
        List<ProductDto> list = new ArrayList<>();
        PreparedStatement ps = prepareStatement(selectProductDtoQuery);
        ps.setLong(1, (long) id);
        ps.executeQuery();
        ResultSet resultSet = ps.getResultSet();
        while (resultSet.next()) {
            ProductDto productDto = new ProductDto();
            productDto.setName(resultSet.getString(1));
            productDto.setPrice(resultSet.getDouble(2));
            productDto.setImage(resultSet.getString(3));
            productDto.setManufacturer(resultSet.getString(4));
            productDto.setMaterial(resultSet.getString(5));
            productDto.setSize(resultSet.getInt(6));
            productDto.setQuantity(resultSet.getInt(7));
            productDto.setDiscount(resultSet.getDouble(8));
            productDto.setItemId(resultSet.getLong(9));

            list.add(productDto);
        }
        return list;
    }
}
