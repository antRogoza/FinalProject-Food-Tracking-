package ua.training.model;

public class Meal {
    private int id;
    private String nameOfTheFood;
    private int idNutritionalValue;
    private int idCategoryOfMeal;

    private Meal() {
    }

    public int getId() {
        return id;
    }

    public String getNameOfTheFood() {
        return nameOfTheFood;
    }

    public int getIdNutritionalValue() {
        return idNutritionalValue;
    }

    public int getIdCategoryOfMeal() { return idCategoryOfMeal; }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", nameOfTheFood='" + nameOfTheFood + '\'' +
                ", idNutritionalValue=" + idNutritionalValue +
                ", idCategoryOfMeal=" + idCategoryOfMeal +
                '}';
    }

    public static Meal.BuilderMeal newBuilder() {
        return new Meal().new BuilderMeal();
    }

    public class BuilderMeal {

        private BuilderMeal() {
        }

        public BuilderMeal setId(int id) {
            Meal.this.id = id;
            return this;
        }

        public BuilderMeal setNameOfTheFood(String nameOfTheFood) {
            Meal.this.nameOfTheFood = nameOfTheFood;
            return this;
        }

        public BuilderMeal setIdNutritionalValue(int idNutritionalValue) {
            Meal.this.idNutritionalValue = idNutritionalValue;
            return this;
        }

        public BuilderMeal setIdCategoryOfMealValue(int idCategoryOfMealValue) {
            Meal.this.idCategoryOfMeal = idCategoryOfMeal;
            return this;
        }

        public Meal build() {
            return Meal.this;
        }
    }

}
