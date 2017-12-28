package ua.training.model;

public class CategoryOfMeal {
    private int id;
    private String type;

    private CategoryOfMeal() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CategoryOfMeal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public static CategoryOfMeal.BuilderCategoryOfMeal newBuilder() {
        return new CategoryOfMeal().new BuilderCategoryOfMeal();
    }

    public class BuilderCategoryOfMeal {

        private BuilderCategoryOfMeal() {
        }

        public BuilderCategoryOfMeal setId(int id) {
            CategoryOfMeal.this.id = id;
            return this;
        }

        public BuilderCategoryOfMeal setType(String type) {
            CategoryOfMeal.this.type = type;
            return this;
        }

        public CategoryOfMeal build(){
            return CategoryOfMeal.this;
        }
    }
}
