package ua.training.model.dao;

import ua.training.model.Role;
import ua.training.model.Client;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

//    private final List<Client> store = new ArrayList<>();
//
//    public Client getById(int id) {
//
//        Client result = new Client();
//        result.setId(-1);
//        for (Client client : store) {
//            if (client.getId() == id) {
//                result = client;
//            }
//        }
//        return result;
//    }
//
//    public Client getUserByLoginPassword(final String login, final String password) {
//        Client result = new Client();
//        result.setId(-1);
//        for (Client client : store) {
//            if (client.getLogin().equals(login) && client.getPassword().equals(password)) {
//                result = client;
//            }
//        }
//        return result;
//    }
//
//    public boolean add(final Client client) {
//        for (Client u : store) {
//            if (u.getLogin().equals(client.getLogin()) && u.getPassword().equals(client.getPassword())) {
//                return false;
//            }
//        }
//        return store.add(client);
//    }
//
//    public Role getRoleByLoginPassword(final String login, final String password) {
//        Role result = Role.UNKNOWN;
//        for (Client client : store) {
//            if (client.getLogin().equals(login) && client.getPassword().equals(password)) {
//                result = client.getRole();
//            }
//        }
//        return result;
//    }
//
//    public boolean userIsExist(final String login, final String password) {
//        boolean result = false;
//        for (Client client : store) {
//            if (client.getLogin().equals(login) && client.getPassword().equals(password)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
}
