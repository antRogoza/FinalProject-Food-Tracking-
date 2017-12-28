package ua.training.model.dao;

import ua.training.model.Criterion;
import ua.training.model.NutritionalValue;
import ua.training.model.WayOfLife;
import ua.training.model.dao.implement.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract MealDao createMealDao();

    public abstract ClientDao createClientDao();

    public abstract LoginDao createLoginDao();

    public abstract WayOfLifeDao createWayOfLifeDao();

    public abstract CriterionDao createCriterionDao();

    public abstract CategoryOfMealDao createCategoryOfMealDao();

    public abstract NutritionalValueDao createNutritionalValueDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

