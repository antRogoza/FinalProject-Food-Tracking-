package ua.training.model.dao.implement;

import ua.training.model.Client;
import ua.training.model.Login;
import ua.training.model.Role;
import ua.training.model.dao.LoginDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCLoginDao implements LoginDao {
    private static final String CREATE = "INSERT INTO  login(ID_LOGIN,E-MAIL,PASSWORD,ID_CLIENT,ID_ROLE)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM login WHERE ID_LOGIN = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM login";
    private static final String QUERY_UPDATE = "UPDATE login SET E-MAIL = ?, PASSWORD = ?," +
            " ID_CLIENT = ?, ID_ROLE = ?";
    private static final String DELETE = "DELETE FROM login WHERE ID_LOGIN = ?";

    private Connection connection;

    public JDBCLoginDao(Connection c) {
        this.connection = c;
    }

    private void forCreateAndUpdate(Login login, String QUERY) {
        try (PreparedStatement ps = connection.prepareStatement
                (QUERY)) {
            ps.setString(1, String.valueOf(login.getId()));
            ps.setString(2, login.getEmail());
            ps.setString(3, login.getPassword());
            ps.setString(4, String.valueOf(login.getIdClient()));
            ps.setString(5, String.valueOf(login.getRole()));

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Login login) {
        forCreateAndUpdate(login, CREATE);
    }

    @Override
    public Login findById(int id) throws SQLException {
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

    private Login extractFromResultSet(ResultSet rs)
            throws SQLException {
        Login result = Login.newBuilder()
                .setId(rs.getInt("ID_LOGIN"))
                .setEmail(rs.getString("E-MAIL"))
                .setPassword(rs.getString("PASSWORD"))
                .setIdClient(rs.getInt("ID_CLIENT"))
                .setRole(rs.getInt("ID_ROLE"))
                .build();
        return result;
    }

    @Override
    public List<Login> findAll() {
        ArrayList<Login> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()) {
            ResultSet rs = ps.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                Login result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Login login) {
        forCreateAndUpdate(login, QUERY_UPDATE);
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
    public void delete(Login login) {
        try (PreparedStatement ps = connection.prepareStatement(
                DELETE)) {
            ps.setInt(1, login.getId());
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
