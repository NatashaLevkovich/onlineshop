package services.impl;


import DAO.ProductSizeDao;
import DAO.impl.ProductSizeDaoImpl;
import entities.ProductSize;
import services.ProductSizeService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSizeServiceImpl extends AbstractService implements ProductSizeService {

    private ProductSizeDao productSizeDao = ProductSizeDaoImpl.getInstance();

    private ProductSizeServiceImpl() {
    }

    private static volatile ProductSizeService INSTANCE = null;

    public static ProductSizeService getInstance() {
        ProductSizeService productSizeService = INSTANCE;
        if (productSizeService == null) {
            synchronized (ProductSizeServiceImpl.class) {
                productSizeService = INSTANCE;
                if (productSizeService == null) {
                    INSTANCE = productSizeService = new ProductSizeServiceImpl();
                }
            }
        }
        return productSizeService;
    }

    @Override
    public int save(ProductSize productSize) {
        int i = 0;
        try {
            i = productSizeDao.save(productSize);
        } catch (SQLException e) {
            System.out.println("Ошибка добавления размера");
        }
        return i;
    }

    @Override
    public ProductSize get(Serializable id) {
        ProductSize productSize = new ProductSize();
        try {
            productSize = productSizeDao.get(id);
        } catch (SQLException e) {
            System.out.println("Ошибка получения размера");
        }
        return productSize;
    }

    @Override
    public int update(ProductSize productSize) {
        int i = 0;
        try {
            i = productSizeDao.update(productSize);
        } catch (SQLException e) {
            System.out.println("Ошибка обновления размера");
        }
        return i;
    }

    @Override
    public int delete(Serializable productId) {
        int i = 0;
        try {
            i = productSizeDao.delete(productId);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления размера");
        }
        return i;
    }

    @Override
    public List<ProductSize> getAll() {
        List<ProductSize> list = new ArrayList<>();
        try {
            list = productSizeDao.getAll();
        } catch (SQLException e){
            System.out.println("Ошибка получения списка размеров");
        }
        return list;
    }

    @Override
    public Map<Integer, Integer> getSizeAndQuantityByProduct(Serializable productId) {
        Map<Integer, Integer> map = new HashMap<>();
        try{
            map = productSizeDao.getSizeAndQuantityByProduct(productId);
        } catch (SQLException e){
            System.out.println("Ошибка получения размеров товара");
        }
        return map;
    }

    @Override
    public ProductSize getSizeByProductAndSize(Serializable productId, int size) {
        ProductSize productSize = new ProductSize();
        try {
            productSize = productSizeDao.getSizeByProductAndSize(productId, size);
        } catch (SQLException e){
            System.out.println("Ошибка получения размера товара");
        }
        return productSize;
    }
}
