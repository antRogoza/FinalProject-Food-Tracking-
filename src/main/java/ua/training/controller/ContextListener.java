package ua.training.controller;

import ua.training.model.Client;
import ua.training.model.dao.UserDAO;
import ua.training.model.dao.implement.JDBCClientDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static ua.training.model.Role.ADMIN;
import static ua.training.model.Role.USER;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
//    /**
//     * Fake database connector.
//     */
//    private AtomicReference<JDBCClientDao> dao;
//
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        ArrayList login = new JDBCClientDao().findAll();
//
//        //dao.get().add(new Client(1, "Anton", "11", ADMIN));
//        //dao.get().add(new Client(2, "Devid", "1", USER));
//
//        final ServletContext servletContext =
//                servletContextEvent.getServletContext();
//
//        servletContext.setAttribute("dao", dao);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        dao = null;
//    }
}
