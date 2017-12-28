package ua.training.model.dao.implement;

import ua.training.model.WayOfLife;
import ua.training.model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public MealDao createMealDao() {
        return new JDBCMealDao(getConnection());
    }

    @Override
    public ClientDao createClientDao() {
        return new JDBCClientDao(getConnection());
    }

    @Override
    public LoginDao createLoginDao() { return new JDBCLoginDao(getConnection()); }

    @Override
    public WayOfLifeDao createWayOfLifeDao() { return new JDBCWayOfLifeDao(getConnection()); }

    @Override
    public CriterionDao createCriterionDao() { return new JDBCCriterionDao(getConnection()); }

    @Override
    public CategoryOfMealDao createCategoryOfMealDao() { return new JDBCCategoryOfMealDao(getConnection()); }

    @Override
    public NutritionalValueDao createNutritionalValueDao() { return new JDBCNutritionalValueDao(getConnection()); }

    private Connection getConnection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tracking_food",
                    "root" ,
                    "antRogoza97" );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
