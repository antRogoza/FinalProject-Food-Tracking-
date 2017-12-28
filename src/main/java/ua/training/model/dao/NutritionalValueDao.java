package ua.training.model.dao;

import ua.training.model.NutritionalValue;
import ua.training.model.WayOfLife;

import java.sql.SQLException;
import java.util.List;

public interface NutritionalValueDao extends AutoCloseable {
    void create(NutritionalValue wayOfLife);
    NutritionalValue findById(int id) throws SQLException;
    List<NutritionalValue> findAll();
    void update(NutritionalValue nutritionalValue);
    void delete(int id);
    void delete(NutritionalValue nutritionalValue);
    void close() throws Exception;
}
