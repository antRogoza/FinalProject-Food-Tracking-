package ua.training;

import ua.training.model.Client;
import ua.training.model.Criterion;
import ua.training.model.Login;
import ua.training.model.WayOfLife;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.implement.JDBCClientDao;
import ua.training.model.dao.implement.JDBCDaoFactory;
import ua.training.view.View;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println(View.bundle.getString(View.THEME_OF_THE_PROJECT));

        Criterion criterion = Criterion.newBuilder()
                .setId(1)
                .setFromHeight(150)
                .setToHeight(160)
                .setFromWeight(45)
                .setToWeight(55)
                .setNumberOfProteins(800)
                .setNumberOfFats(500)
                .setNumberOfCarbohydrates(1000)
                .build();
        Criterion criterion1 = Criterion.newBuilder()
                .setId(999)
                .setFromHeight(160)
                .setToHeight(1)
                .setFromWeight(4)
                .setToWeight(5)
                .setNumberOfProteins(8)
                .setNumberOfFats(5)
                .setNumberOfCarbohydrates(1)
                .build();
        System.out.println(criterion.hashCode() + " second: " + criterion1.hashCode());
        System.out.println(criterion + "\n" + criterion1);

        List<Client> clients = new JDBCDaoFactory().createClientDao().findAll();
        List<Login> logins = new JDBCDaoFactory().createLoginDao().findAll();
        List<WayOfLife> wayOfLifes = new JDBCDaoFactory().createWayOfLifeDao().findAll();
        System.out.println(clients);
        System.out.println(wayOfLifes);
        System.out.println(logins);
        try {
            System.out.println(new JDBCDaoFactory().createLoginDao().findById(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
