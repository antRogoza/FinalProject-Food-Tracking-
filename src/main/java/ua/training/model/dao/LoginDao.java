package ua.training.model.dao;

import ua.training.model.Login;

import java.sql.SQLException;
import java.util.List;

public interface LoginDao extends AutoCloseable{
    void create(Login login);
    Login findById(int id) throws SQLException;
    List<Login> findAll();
    void update(Login login);
    void delete(int id);
    void delete(Login login);
    void close() throws Exception;
}
