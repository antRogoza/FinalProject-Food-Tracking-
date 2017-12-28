package ua.training.model.dao.implement;

import ua.training.model.Login;
import ua.training.model.WayOfLife;
import ua.training.model.dao.WayOfLifeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCWayOfLifeDao implements WayOfLifeDao{
    private static final String CREATE = "INSERT INTO  way_of_life(ID_WAY_OF_LIFE, TYPE(EN))"
            + " VALUES (?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM way_of_life WHERE ID_WAY_OF_LIFE = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM way_of_life";
    private static final String QUERY_UPDATE = "UPDATE way_of_life SET TYPE(EN) = ?";
    private static final String DELETE = "DELETE FROM way_of_life WHERE ID_WAY_OF_LIFE = ?";

    private Connection connection;

    public JDBCWayOfLifeDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(WayOfLife wayOfLife, String QUERY) {
        try (PreparedStatement ps = connection.prepareStatement
                (QUERY)) {
            ps.setString(1, String.valueOf(wayOfLife.getId()));
            ps.setString(2, wayOfLife.getType());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(WayOfLife wayOfLife) {
        forCreateAndUpdate(wayOfLife, CREATE);
    }

    @Override
    public WayOfLife findById(int id) throws SQLException {
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

    private WayOfLife extractFromResultSet(ResultSet rs)
            throws SQLException {
        WayOfLife result = WayOfLife.newBuilder()
                .setId(rs.getInt("ID_WAY_OF_LIFE"))
                .setType(rs.getString("TYPE(EN)"))
                .build();
        return result;
    }

    @Override
    public List<WayOfLife> findAll() {
        ArrayList<WayOfLife> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                WayOfLife result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(WayOfLife wayOfLife) {
        forCreateAndUpdate(wayOfLife, QUERY_UPDATE);
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
    public void delete(WayOfLife wayOfLife) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, wayOfLife.getId());
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
