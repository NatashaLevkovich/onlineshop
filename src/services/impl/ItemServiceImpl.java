package services.impl;

import dao.*;
import dao.impl.*;
import entities.*;
import services.ItemService;
import services.OrderService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl extends AbstractService implements ItemService {

    private ProductDao productDao = ProductDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();
    private ProductSizeDao productSizeDao = ProductSizeDaoImpl.getInstance();
    private OrderService orderService = OrderServiceImpl.getInstance();

    private ItemServiceImpl() {
    }

    private static volatile ItemService INSTANCE = null;

    public static ItemService getInstance() {
        ItemService itemService = INSTANCE;
        if (itemService == null) {
            synchronized (ItemServiceImpl.class) {
                itemService = INSTANCE;
                if (itemService == null) {
                    INSTANCE = itemService = new ItemServiceImpl();
                }
            }
        }
        return itemService;
    }

    @Override
    public int save(Order order, long productId, int productSize, int quantity) {
        int i = 0;
        try {
            startTransaction();
            Product product = productDao.get(productId);
            ProductSize size = productSizeDao.getSizeByProductAndSize(productId, productSize);
            size.setQuantity(size.getQuantity() - quantity);
            productSizeDao.update(size);
            User user = userDao.get(order.getUserId());
            if (product.getDiscount() > 0) {
                double price = (product.getPrice() - (product.getPrice() * product.getDiscount()) * quantity);
                Item item = new Item(productId, productSize, order.getId(), quantity, product.getDiscount());
                itemDao.save(item);
            } else {
                double price = (product.getPrice() - (product.getPrice() * user.getDiscount()) * quantity);
                Item item = new Item(productId, productSize, order.getId(), quantity, user.getDiscount());
                itemDao.save(item);
            }
            orderService.update(order);
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка изменения заказа");
        }
        return i;
    }

    @Override
    public Item get(Serializable id) {
        Item item = new Item();
        try {
            item = itemDao.get(id);
        } catch (SQLException e) {
            System.out.println("Ошибка получения товара");
        }
        return item;
    }

    @Override
    public int update(Item item, Order order, int quantity) {
        int i = 0;
        try {
            startTransaction();
            i = itemDao.update(item);
            orderService.update(order);
              ProductSize productSize = productSizeDao.getSizeByProductAndSize(item.getProductId(), item.getProductSize());
              productSize.setQuantity(productSize.getQuantity() - quantity);
              productSizeDao.update(productSize);
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка обновления товара");
        }
        return i;
    }

    @Override
    public int delete(Item item) {
        int i = 0;
        try {
            i = itemDao.delete(item);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления товара");
        }
        return i;
    }

    @Override
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        try {
            list = itemDao.getAll();
        } catch (SQLException e){
            System.out.println("Ошибка получения списка товаров");
        }
        return list;
    }

    @Override
    public List<Item> getItemsByOrder(Serializable orderId) {
        List<Item> list = new ArrayList<>();
        try {
            list = itemDao.getItemsByOrder(orderId);
        } catch (SQLException e){
            System.out.println("Ошибка получения списка товаров");
        }
        return list;
    }
}
