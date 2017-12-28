package ua.training.model.dao.implement;

import ua.training.model.Meal;
import ua.training.model.dao.MealDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMealDao implements MealDao {
    private static final String CREATE = "INSERT INTO  meal(ID_MEAL,NAME_OF_THE_FOOD(EN),ID_NUTRITIONAL_VALUE," +
            " ID_CATEGORY_OF_MEAL) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM meal WHERE ID_MEAL = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM meal";
    private static final String QUERY_UPDATE = "UPDATE meal SET NAME_OF_THE_FOOD(EN) = ?, ID_NUTRITIONAL_VALUE = ?," +
            " ID_CATEGORY_OF_MEAL = ?";
    private static final String DELETE = "DELETE FROM meal WHERE ID_MEAL = ?";

    private Connection connection;

    public JDBCMealDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(Meal meal, String QUERY) {
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY)) {
            ps.setString(1, String.valueOf(meal.getId()));
            ps.setString(2, meal.getNameOfTheFood());
            ps.setString(3, String.valueOf(meal.getIdNutritionalValue()));
            ps.setString(4, String.valueOf(meal.getIdCategoryOfMeal()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Meal meal) {
        forCreateAndUpdate(meal, CREATE);
    }

    @Override
    public Meal findById(int id) throws SQLException {
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

    private Meal extractFromResultSet(ResultSet rs)
            throws SQLException {
        Meal result = Meal.newBuilder()
                .setId(rs.getInt("ID_MEAL"))
                .setNameOfTheFood(rs.getString("NAME_OF_THE_FOOD(EN)"))
                .setIdNutritionalValue(rs.getInt("ID_NUTRITIONAL_VALUE"))
                .setIdCategoryOfMealValue(rs.getInt("ID_CATEGORY_OF_MEAL"))
                .build();
        return result;
    }

    @Override
    public List<Meal> findAll() {
        ArrayList<Meal> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                Meal result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Meal meal) {
        forCreateAndUpdate(meal, QUERY_UPDATE);
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
    public void delete(Meal meal) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, meal.getId());
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
