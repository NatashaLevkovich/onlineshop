package services.impl;

import db.ConnectionDB;

import java.sql.SQLException;

public abstract class AbstractService {

    public void startTransaction() throws SQLException {
        ConnectionDB.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionDB.getConnection().commit();
    }

    public void rollback(){
        try {
            ConnectionDB.getConnection().rollback();
        } catch (SQLException e) {
            System.out.println("ошибка отката транзакции");
        }
    }
}
