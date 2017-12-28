package ua.training.model.dao;

import ua.training.model.Client;
import ua.training.model.Criterion;
import ua.training.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao extends AutoCloseable{
    Order getOrder(int id);
    List<Criterion> findAllOrderOfClient(Client client);
    List<Date> findAllDatesClientOvereat(Client client);

}
