package ua.training.model.dao;

import ua.training.model.Criterion;
import ua.training.model.WayOfLife;

import java.sql.SQLException;
import java.util.List;

public interface CriterionDao extends AutoCloseable{
    void create(Criterion wayOfLife);
    Criterion findById(int id) throws SQLException;
    List<Criterion> findAll();
    void update(Criterion criterion);
    void delete(int id);
    void delete(Criterion criterion);
    void close() throws Exception;
}
