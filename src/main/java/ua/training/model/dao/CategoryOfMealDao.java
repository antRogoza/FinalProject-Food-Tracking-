package ua.training.model.dao;

import ua.training.model.CategoryOfMeal;
import ua.training.model.WayOfLife;

import java.sql.SQLException;
import java.util.List;

public interface CategoryOfMealDao extends AutoCloseable {
    void create(CategoryOfMeal wayOfLife);
    CategoryOfMeal findById(int id) throws SQLException;
    List<CategoryOfMeal> findAll();
    void update(CategoryOfMeal categoryOfMeal);
    void delete(int id);
    void delete(CategoryOfMeal categoryOfMeal);
    void close() throws Exception;
}
