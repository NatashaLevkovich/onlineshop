package services.impl;

import DAO.ProductDao;
import DAO.ProductSizeDao;
import DAO.impl.ProductDaoImpl;
import DAO.impl.ProductSizeDaoImpl;
import entities.Product;
import entities.ProductCategory;
import entities.ProductSize;
import entities.ProductSubcategory;
import services.ProductService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl extends AbstractService implements ProductService {

    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ProductSizeDao productSizeDao = ProductSizeDaoImpl.getInstance();

    private ProductServiceImpl() {
    }

    private static volatile ProductService INSTANCE = null;

    public static ProductService getInstance() {
        ProductService productService = INSTANCE;
        if (productService == null) {
            synchronized (ProductServiceImpl.class) {
                productService = INSTANCE;
                if (productService == null) {
                    INSTANCE = productService = new ProductServiceImpl();
                }
            }
        }
        return productService;
    }

    @Override
    public int save(Product product) {
        int i = 0;
        try {
            startTransaction();
            i = productDao.save(product);
            for (Map.Entry<Integer, Integer> map : product.getSizeAndQuantity().entrySet()) {
                ProductSize productSize = new ProductSize(product.getId(), map.getKey(), map.getValue());
                productSizeDao.save(productSize);
            }
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка добавления товара");
        }
        return i;
    }

    @Override
    public Product get(Serializable id) {
        Product product = new Product();
        try {
            product = productDao.get(id);
            product.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(product.getId()));
        } catch (SQLException e) {
            System.out.println("Ошибка получения товара с id = " + id);
        }
        return product;
    }

    @Override
    public int update(Product product) {
        int i = 0;
        try {
            startTransaction();
            i = productDao.update(product);
            for (Map.Entry<Integer, Integer> map : product.getSizeAndQuantity().entrySet()) {
                ProductSize productSize = productSizeDao.getSizeByProductAndSize(product.getId(), map.getKey());
                if (productSize.getProductId() == product.getId()) {
                    productSize.setQuantity(map.getValue());
                    productSizeDao.update(productSize);
                } else {
                    productSize = new ProductSize(product.getId(), map.getKey(), map.getValue());
                    productSizeDao.save(productSize);
                }
            }
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка обновления товара");
        }
        return i;
    }

    @Override
    public int delete(Product product) {
        int i = 0;
        try {
            startTransaction();
            i = productDao.delete(product);
            productSizeDao.delete(product.getId());
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка удаления товара");
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try {
            list = productDao.getAll();
            for (Product p : list) {
                p.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(p.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения списка товаров");
        }
        return list;
    }

    @Override
    public List<Product> getProductsByDiscount(double discount) {
        List<Product> list = new ArrayList<>();
        try {
            list = productDao.getProductsByDiscount(discount);
            for (Product p : list) {
                p.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(p.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения списка акционных товаров");
        }
        return list;
    }

    @Override
    public Product getProductByName(String name) {
        Product product = new Product();
        try {
            product = productDao.getProductByName(name);
            product.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(product.getId()));
        } catch (SQLException e) {
            System.out.println("Ошибка получения товара с name = " + name);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory category) {
        List<Product> list = new ArrayList<>();
        try {
            list = productDao.getProductsByCategory(category);
            for (Product p : list) {
                p.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(p.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения списка товаров катогии " + category);
        }
        return list;
    }

    @Override
    public List<Product> getProductsBySubcategory(String subcategory) {
        List<Product> list = new ArrayList<>();
        try {
            list = productDao.getProductsBySubcategory(subcategory);
            for (Product p : list) {
                p.setSizeAndQuantity(productSizeDao.getSizeAndQuantityByProduct(p.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения списка товаров катгории " + subcategory);
        }
        return list;
    }
}
