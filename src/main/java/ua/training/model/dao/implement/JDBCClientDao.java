package ua.training.model.dao.implement;

import ua.training.model.Client;
import ua.training.model.dao.ClientDao;
import ua.training.view.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCClientDao implements ClientDao {
    private static final String CREATE = "INSERT INTO  client(ID_CLIENT,FIRSTNAME(EN),LASTNAME(EN),HEIGHT,ID_NORM)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM client WHERE ID_CLIENT = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM client";
    private static final String QUERY_UPDATE = "UPDATE client SET FIRSTNAME(EN) = ?, LASTNAME(EN) = ?," +
            " HEIGHT = ?, ID_NORM = ?";
    private static final String DELETE = "DELETE FROM client WHERE ID_CLIENT = ?";

    private Connection connection;

    public JDBCClientDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(Client client, String QUERY){
        try (PreparedStatement ps = connection.prepareStatement(
                QUERY)) {
            ps.setString(1, String.valueOf(client.getId()));
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, String.valueOf(client.getHeight()));
            ps.setString(5, String.valueOf(client.getIdCriterion()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Client client) {
        forCreateAndUpdate(client,CREATE);
    }

    @Override
    public Client findById(int id) throws SQLException {
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

    private Client extractFromResultSet(ResultSet rs)
            throws SQLException {
        Client result = Client.newBuilder()
                .setId(rs.getInt("ID_CLIENT"))
                .setFirstName(rs.getString("FIRSTNAME(EN)"))
                .setLastName(rs.getString("LASTNAME(EN)"))
                .setHeight(rs.getInt("HEIGHT"))
                .setIdCriterion(rs.getInt("ID_NORM"))
                .build();
        return result;
    }

    @Override
    public List<Client> findAll() {
        ArrayList<Client> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                Client result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Client client) {
       forCreateAndUpdate(client,QUERY_UPDATE);
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
    public void delete(Client client) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, client.getId());
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
