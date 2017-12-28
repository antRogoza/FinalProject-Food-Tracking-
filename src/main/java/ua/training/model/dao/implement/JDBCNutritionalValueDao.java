package ua.training.model.dao.implement;

import ua.training.model.Client;
import ua.training.model.NutritionalValue;
import ua.training.model.dao.NutritionalValueDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCNutritionalValueDao implements NutritionalValueDao {
    private static final String CREATE = "INSERT INTO  nutritional_value(ID_NUTRITIONAL_VALUE,NUMBER_OF_PROTEINS," +
            "NUMBER_OF_FATS,NUMBER_OF_CARBOHYDRATES) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM nutritional_value WHERE ID_NUTRITIONAL_VALUE = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM nutritional_value";
    private static final String QUERY_UPDATE = "UPDATE client SET NUMBER_OF_PROTEINS = ?, NUMBER_OF_FATS = ?," +
            " NUMBER_OF_CARBOHYDRATES = ?";
    private static final String DELETE = "DELETE FROM nutritional_value WHERE ID_NUTRITIONAL_VALUE = ?";

    private Connection connection;

    public JDBCNutritionalValueDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(NutritionalValue nutritionalValue, String QUERY) {
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY)) {
            ps.setString(1, String.valueOf(nutritionalValue.getId()));
            ps.setString(2, String.valueOf(nutritionalValue.getNumberOfProteins()));
            ps.setString(3, String.valueOf(nutritionalValue.getNumberOfFats()));
            ps.setString(4, String.valueOf(nutritionalValue.getNumberOfCarbohydrates()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(NutritionalValue nutritionalValue) {
        forCreateAndUpdate(nutritionalValue, CREATE);
    }

    @Override
    public NutritionalValue findById(int id) throws SQLException {
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

    private NutritionalValue extractFromResultSet(ResultSet rs)
            throws SQLException {
        NutritionalValue result = NutritionalValue.newBuilder()
                .setId(rs.getInt("ID_NUTRITIONAL_VALUE"))
                .setNumberOfProteins(rs.getInt("NUMBER_OF_PROTEINS"))
                .setNumberOfFats(rs.getInt("NUMBER_OF_FATS"))
                .setNumberOfCarbohydrates(rs.getInt("NUMBER_OF_CARBOHYDRATES"))
                .build();
        return result;
    }

    @Override
    public List<NutritionalValue> findAll() {
        ArrayList<NutritionalValue> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                NutritionalValue result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(NutritionalValue nutritionalValue) {
        forCreateAndUpdate(nutritionalValue, QUERY_UPDATE);
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
    public void delete(NutritionalValue nutritionalValue) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, nutritionalValue.getId());
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
