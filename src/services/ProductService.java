package services;

import entities.Product;
import entities.ProductCategory;
import entities.ProductSubcategory;
import java.io.Serializable;
import java.util.List;

public interface ProductService {
    int save(Product product);

    Product get(Serializable id);

    int update(Product product);

    int delete(Product product);

    List<Product> getAll();

    Product getProductByName(String name);

    List<Product> getProductsByCategory(ProductCategory category);

    List<Product> getProductsBySubcategory(String subcategory);

    List<Product> getProductsByDiscount(double discount);
}
