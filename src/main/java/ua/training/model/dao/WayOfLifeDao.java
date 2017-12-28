package ua.training.model.dao;

import ua.training.model.Login;
import ua.training.model.WayOfLife;

import java.sql.SQLException;
import java.util.List;

public interface WayOfLifeDao extends AutoCloseable{
    void create(WayOfLife wayOfLife);
    WayOfLife findById(int id) throws SQLException;
    List<WayOfLife> findAll();
    void update(WayOfLife login);
    void delete(int id);
    void delete(WayOfLife login);
    void close() throws Exception;
}
