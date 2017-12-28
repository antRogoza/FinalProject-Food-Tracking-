package ua.training.model.dao.implement;

import ua.training.model.CategoryOfMeal;
import ua.training.model.Client;
import ua.training.model.dao.CategoryOfMealDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCategoryOfMealDao implements CategoryOfMealDao {
    private static final String CREATE = "INSERT INTO  category_of_meal(ID_CATEGORY_OF_MEAL,TYPE(EN)"
            + " VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM category_of_meal WHERE ID_CATEGORY_OF_MEAL = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM category_of_meal";
    private static final String QUERY_UPDATE = "UPDATE client SET TYPE(EN) = ?";
    private static final String DELETE = "DELETE FROM category_of_meal WHERE ID_CATEGORY_OF_MEAL = ?";

    private Connection connection;

    public JDBCCategoryOfMealDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(CategoryOfMeal categoryOfMeal, String QUERY){
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY)) {
            ps.setString(1, String.valueOf(categoryOfMeal.getId()));
            ps.setString(2, categoryOfMeal.getType());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(CategoryOfMeal categoryOfMeal) {
        forCreateAndUpdate(categoryOfMeal,CREATE);
    }

    @Override
    public CategoryOfMeal findById(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement
                (FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private CategoryOfMeal extractFromResultSet(ResultSet rs)
            throws SQLException {
        CategoryOfMeal result = CategoryOfMeal.newBuilder()
                .setId(rs.getInt("ID_CATEGORY_OF_MEAL"))
                .setType(rs.getString("TYPE(EN)"))
                .build();
        return result;
    }

    @Override
    public List<CategoryOfMeal> findAll() {
        ArrayList<CategoryOfMeal> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                CategoryOfMeal result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(CategoryOfMeal categoryOfMeal) {
        forCreateAndUpdate(categoryOfMeal,QUERY_UPDATE);
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CategoryOfMeal categoryOfMeal) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, categoryOfMeal.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
