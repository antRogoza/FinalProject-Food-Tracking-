package ua.training.model.dao;

import ua.training.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao extends AutoCloseable{
        void create(Client client);
        Client findById(int id) throws SQLException;
        List<Client> findAll();
        void update(Client client);
        void delete(int id);
        void delete(Client client);
        void close() throws Exception;
}
