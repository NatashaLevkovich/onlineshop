package services.impl;

import dao.OrderDao;
import dao.UserDao;
import dao.impl.OrderDaoImpl;
import dao.impl.UserDaoImpl;
import entities.User;
import services.UserService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends AbstractService implements UserService {
    private UserDao userDao = UserDaoImpl.getInstance();
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    private static volatile UserService INSTANCE = null;

    public static UserService getInstance() {
        UserService userService = INSTANCE;
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                userService = INSTANCE;
                if (userService == null) {
                    INSTANCE = userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public User save(User user) {
        try {
           userDao.save(user);
        } catch (SQLException e) {
            System.out.println("Ошибка добавления клиента");
        }
        return user;
    }

    @Override
    public User get(Serializable id) {
        User user = new User();
        try {
            user = userDao.get(id);
        } catch (SQLException e) {
            System.out.println("Ошибка получения клиента с id = " + id);
        }
        return user;
    }

    @Override
    public int update(User user) {
        int i = 0;
        try {
            i = userDao.update(user);
        } catch (SQLException e) {
            System.out.println("Ошибка обновления клиента " + user);
        }
        return i;
    }

    @Override
    public int delete(User user) {
        int i = 0;
        try {
            i = userDao.delete(user);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления клиента " + user);
        }
        return i;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            list = userDao.getAll();
        } catch (SQLException e) {
            System.out.println("Ошибка получения спика клиентов");
            ;
        }
        return list;
    }

    @Override
    public int updatePassword(User user) {
        int i = 0;
        try {
            i = userDao.updatePassword(user);
        } catch (SQLException e) {
            System.out.println("Ошибка обновления клиента " + user);
        }
        return i;
    }

    @Override
    public int updateEmail(User user) {
        int i = 0;
        try {
            i = userDao.updateEmail(user);
        } catch (SQLException e) {
            System.out.println("Ошибка обновления клиента " + user);
        }
        return i;
    }

    @Override
    public int setDiscount(User user) {
        int i = 0;
        try {
            userDao.setDiscount(user);
        } catch (SQLException e) {

            System.out.println("Ошибка добавления скидки");
        }
        return i;
    }

    @Override
    public int setStatus(User user) {
        int i = 0;
        try {
            i = userDao.setStatus(user);
        } catch (SQLException e) {
            System.out.println("Ошибка обновления статуса клиента " + user);
        }
        return i;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        try {
            user = userDao.getUserByEmailAndPassword(email);
        } catch (SQLException e) {
            System.out.println("Ошибка получения клиента с login = " + email);
        }
        return user;
    }
}
