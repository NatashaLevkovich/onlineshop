package services.impl;

import dao.dto.ProductDtoDao;
import entities.dto.ProductDto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDtoService extends AbstractService {
    ProductDtoDao productDtoDao = ProductDtoDao.getInstance();

    private static volatile ProductDtoService INSTANCE = null;

    public static ProductDtoService getInstance() {
        ProductDtoService productDtoService = INSTANCE;
        if (productDtoService == null) {
            synchronized (ProductDtoService.class) {
                productDtoService = INSTANCE;
                if (productDtoService == null) {
                    INSTANCE = productDtoService = new ProductDtoService();
                }
            }
        }
        return productDtoService;
    }

    public List<ProductDto> get(Serializable order_id){
        List<ProductDto> productDto = new ArrayList<>();
        try {
            productDto = productDtoDao.get(order_id);
        } catch (SQLException e){
            System.out.println("Ошибка ProductDto");
        }
        return productDto;
    }
}
