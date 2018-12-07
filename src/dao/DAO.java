package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    int save(T t) throws SQLException;

    T get(Serializable id) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;

    List<T> getAll() throws SQLException;
}
