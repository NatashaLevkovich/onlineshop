package services.impl;

import dao.*;
import dao.impl.*;
import entities.*;
import services.OrderService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl extends AbstractService implements OrderService {
    private OrderDao orderDao = OrderDaoImpl.getInstance();
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private ItemDao itemDao = ItemDaoImpl.getInstance();
    private ProductSizeDao productSizeDao = ProductSizeDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    private static volatile OrderService INSTANCE = null;

    public static OrderService getInstance() {
        OrderService orderService = INSTANCE;
        if (orderService == null) {
            synchronized (OrderServiceImpl.class) {
                orderService = INSTANCE;
                if (orderService == null) {
                    INSTANCE = orderService = new OrderServiceImpl();
                }
            }
        }
        return orderService;
    }

    @Override
    public Order save(long userId, long productId, int productSize, int quantity) {
        Order order = new Order();
        Calendar calendar = Calendar.getInstance();
        try {
            startTransaction();
            calendar.setTime(new Date());
            order.setUserId(userId);
            order.setOrderDate(calendar);
            Product product = productDao.get(productId);
            ProductSize size = productSizeDao.getSizeByProductAndSize(productId, productSize);
            size.setQuantity(size.getQuantity() - quantity);
            productSizeDao.update(size);
            User user = userDao.get(userId);
            order.setStatus(Status.NEW);
            orderDao.save(order);
            if (product.getDiscount() > 0) {
                Item item = new Item(productId, productSize, order.getId(), quantity, product.getDiscount());
                itemDao.save(item);
            } else {
                Item item = new Item(productId, productSize, order.getId(), quantity, product.getDiscount());
                itemDao.save(item);
            }
            update(order);
            commit();
        } catch (SQLException e) {
            rollback();
            System.out.println("Ошибка создания заказа");
        }
        return order;
    }

    @Override
    public Order get(Serializable id) {
        Order order = new Order();
        try {
            order = orderDao.get(id);
            order.setItems(itemDao.getItemsByOrder(order.getId()));
        } catch (SQLException e) {
            System.out.println("Ошибка получения заказа");
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        try {
            List<Item> items = itemDao.getItemsByOrder(order.getId());
            double totalPrice = 0;
            for (Item item : items){
                double price = productDao.get(item.getProductId()).getPrice();
                price = (price - (price * item.getDiscount())) * item.getQuantity();
                totalPrice += price;
            }
            order.setTotalPrice(totalPrice);
            orderDao.update(order);
        } catch (SQLException e){
            System.out.println("Ошибка обновления цены");
        }
        return order;
    }

    @Override
    public int delete(Order order) {
        int i = 0;
        try {
            i = orderDao.delete(order);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления заказа");
        }
        return i;
    }

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        try {
            list = orderDao.getAll();
            for (Order order : list) {
                order.setItems(itemDao.getItemsByOrder(order.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех заказов");
        }
        return list;
    }

    @Override
    public List<Order> getOrdersByUser(long userId) {
        List<Order> list = new ArrayList<>();
        try {
            list = orderDao.getOrdersByUser(userId);
            for (Order order : list) {
                order.setItems(itemDao.getItemsByOrder(order.getId()));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех заказов клиента");
        }
        return list;
    }

    @Override
    public Order getOrdersByUserAndStatus(long userId, Status status) {
        Order order = new Order();
        try {
            order = orderDao.getOrdersByUserAndStatus(userId, status);
            order.setItems(itemDao.getItemsByOrder(order.getId()));
        } catch (SQLException e) {
            System.out.println("Ошибка получения заказа");
        }
        return order;
    }


}
