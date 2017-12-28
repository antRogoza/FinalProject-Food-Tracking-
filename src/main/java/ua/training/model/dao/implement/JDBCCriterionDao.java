package ua.training.model.dao.implement;

import ua.training.model.Criterion;
import ua.training.model.dao.CriterionDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCriterionDao implements CriterionDao {
    private static final String CREATE = "INSERT INTO  criterion" + "(ID_NORM,FROM_HEIGHT,TO_HEIGHT,FROM_WEIGHT," +
            "TO_WEIGHT,NUMBER_OF_PROTEINS,NUMBER_OF_FATS,NUMBER_OF_CARBOHYDRATES) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM norm WHERE ID_NORM = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM norm";
    private static final String QUERY_UPDATE = "UPDATE norm SET FROM_HEIGHT = ?, TO_HEIGHT = ?, FROM_WEIGHT = ?," +
            " TO_WEIGHT = ?, NUMBER_OF_PROTEINS = ?, NUMBER_OF_FATS = ?, NUMBER_OF_CARBOHYDRATES = ?";
    private static final String DELETE = "DELETE FROM norm WHERE ID_NORM = ?";

    private Connection connection;

    public JDBCCriterionDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(Criterion criterion, String QUERY) {
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY)) {
            ps.setString(1, String.valueOf(criterion.getId()));
            ps.setString(2, String.valueOf(criterion.getFromHeight()));
            ps.setString(3, String.valueOf(criterion.getToHeight()));
            ps.setString(4, String.valueOf(criterion.getFromWeight()));
            ps.setString(5, String.valueOf(criterion.getToWeight()));
            ps.setString(6, String.valueOf(criterion.getNumberOfProteins()));
            ps.setString(7, String.valueOf(criterion.getNumberOfFats()));
            ps.setString(8, String.valueOf(criterion.getNumberOfCarbohydrates()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Criterion criterion) {
        forCreateAndUpdate(criterion, CREATE);
    }

    @Override
    public Criterion findById(int id) throws SQLException {
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

    private Criterion extractFromResultSet(ResultSet rs)
            throws SQLException {
        Criterion result = Criterion.newBuilder()
                .setId(rs.getInt("ID_CLIENT"))
                .setFromHeight(rs.getInt("FROM_HEIGHT"))
                .setToHeight(rs.getInt("TO_HEIGHT"))
                .setFromWeight(rs.getInt("FROM_WEIGHT"))
                .setToWeight(rs.getInt("TO_WEIGHT"))
                .setNumberOfProteins(rs.getInt("NUMBER_OF_PROTEINS"))
                .setNumberOfFats(rs.getInt("NUMBER_OF_FATS"))
                .setNumberOfCarbohydrates(rs.getInt("NUMBER_OF_CARBOHYDRATES"))
                .build();
        return result;
    }

    @Override
    public List<Criterion> findAll() {
        ArrayList<Criterion> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                Criterion result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Criterion criterion) {
        forCreateAndUpdate(criterion, QUERY_UPDATE);
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
    public void delete(Criterion criterion) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, criterion.getId());
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
