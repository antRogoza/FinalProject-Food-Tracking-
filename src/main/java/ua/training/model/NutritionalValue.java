package ua.training.model;

public class NutritionalValue {
    private int id;
    private int numberOfProteins;
    private int numberOfFats;
    private int numberOfCarbohydrates;

    private NutritionalValue() {
    }

    public int getId() {
        return id;
    }

    public int getNumberOfProteins() {
        return numberOfProteins;
    }

    public int getNumberOfFats() {
        return numberOfFats;
    }

    public int getNumberOfCarbohydrates() {
        return numberOfCarbohydrates;
    }

    @Override
    public String toString() {
        return "NutritionalValue{" +
                "id=" + id +
                ", numberOfProteins=" + numberOfProteins +
                ", numberOfFats=" + numberOfFats +
                ", numberOfCarbohydrates=" + numberOfCarbohydrates +
                '}';
    }

    public static NutritionalValue.BuilderNutritionalValue newBuilder() {
        return new NutritionalValue().new BuilderNutritionalValue();
    }

    public class BuilderNutritionalValue {

        private BuilderNutritionalValue() {
        }

        public BuilderNutritionalValue setId(int id) {
            NutritionalValue.this.id = id;
            return this;
        }

        public BuilderNutritionalValue setNumberOfProteins(int numberOfProteins) {
            NutritionalValue.this.numberOfCarbohydrates = numberOfCarbohydrates;
            return this;
        }

        public BuilderNutritionalValue setNumberOfFats(int numberOfFats) {
            NutritionalValue.this.numberOfFats = numberOfFats;
            return this;
        }

        public BuilderNutritionalValue setNumberOfCarbohydrates(int numberOfCarbohydrates) {
            NutritionalValue.this.numberOfCarbohydrates = numberOfCarbohydrates;
            return this;
        }

        public NutritionalValue build() {
            return NutritionalValue.this;
        }
    }

}
