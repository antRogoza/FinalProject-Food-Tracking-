package ua.training.model;

public class Criterion {
    private int id;
    private int fromWeight;
    private int toWeight;
    private int fromHeight;
    private int toHeight;
    private int numberOfProteins;
    private int numberOfFats;
    private int numberOfCarbohydrates;

    private Criterion() {
    }

    public int getId() {
        return id;
    }

    public int getFromWeight() {
        return fromWeight;
    }

    public int getToWeight() {
        return toWeight;
    }

    public int getFromHeight() {
        return fromHeight;
    }

    public int getToHeight() {
        return toHeight;
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
        return "Criterion{" +
                "id=" + id +
                ", fromWeight=" + fromWeight +
                ", toWeight=" + toWeight +
                ", fromHeight=" + fromHeight +
                ", toHeight=" + toHeight +
                ", numberOfProteins=" + numberOfProteins +
                ", numberOfFats=" + numberOfFats +
                ", numberOfCarbohydrates=" + numberOfCarbohydrates +
                '}';
    }

    public static BuilderCriterion newBuilder() {
        return new Criterion().new BuilderCriterion();
    }

    public class BuilderCriterion {

        private BuilderCriterion() {
        }

        public BuilderCriterion setId(int id) {
            Criterion.this.id = id;
            return this;
        }

        public BuilderCriterion setFromHeight(int fromHeight) {
            Criterion.this.fromHeight = fromHeight;
            return this;
        }

        public BuilderCriterion setToHeight(int toHeight) {
            Criterion.this.toHeight = toHeight;
            return this;
        }

        public BuilderCriterion setFromWeight(int fromWeight) {
            Criterion.this.fromWeight = fromWeight;
            return this;
        }

        public BuilderCriterion setToWeight(int toWeight) {
            Criterion.this.toWeight = toWeight;
            return this;
        }

        public BuilderCriterion setNumberOfProteins(int numberOfProteins) {
            Criterion.this.numberOfProteins = numberOfProteins;
            return this;
        }

        public BuilderCriterion setNumberOfFats(int numberOfFats) {
            Criterion.this.numberOfFats = numberOfFats;
            return this;
        }

        public BuilderCriterion setNumberOfCarbohydrates(int numberOfCarbohydrates) {
            Criterion.this.numberOfCarbohydrates = numberOfCarbohydrates;
            return this;
        }

        public Criterion build() {
            return Criterion.this;
        }
    }
}
