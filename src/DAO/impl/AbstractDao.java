package DAO.impl;

import db.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDao {
    protected PreparedStatement prepareStatement(String query) throws SQLException {
        return ConnectionDB.getConnection().prepareStatement(query);
    }

    protected PreparedStatement prepareStatement(String query, int flag) throws SQLException {
        return ConnectionDB.getConnection().prepareStatement(query, flag);
    }
}
