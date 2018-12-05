package services;

import entities.ProductSize;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface ProductSizeService {
    int save(ProductSize productSize);

    ProductSize get(Serializable id);

    int update(ProductSize productSize);

    int delete(Serializable productId);

    List<ProductSize> getAll();

    Map<Integer, Integer> getSizeAndQuantityByProduct(Serializable productId);

    ProductSize getSizeByProductAndSize(Serializable productId, int size);
}
