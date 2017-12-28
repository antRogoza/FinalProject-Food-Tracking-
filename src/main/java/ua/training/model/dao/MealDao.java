package ua.training.model.dao;

import ua.training.model.Meal;
import ua.training.model.WayOfLife;

import java.sql.SQLException;
import java.util.List;

public interface MealDao extends AutoCloseable {
        void create(Meal wayOfLife);
        Meal findById(int id) throws SQLException;
        List<Meal> findAll();
        void update(Meal meal);
        void delete(int id);
        void delete(Meal meal);
        void close() throws Exception;
    }
