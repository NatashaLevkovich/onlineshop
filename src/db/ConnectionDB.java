package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;

public class ConnectionDB {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() {
        try {
            if (threadLocal.get() == null) {
                threadLocal.set(DataSource.getInstance().getConnection());
            }
            return threadLocal.get();
        } catch (Exception e) {
            System.out.println("Ошибка получения соединения " +  e.getMessage());
        }
        return null;
    }

}
