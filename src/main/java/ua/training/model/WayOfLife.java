package ua.training.model;

public class WayOfLife {
    private int id;
    private String type;

    private WayOfLife() {
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "WayOfLife{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public static WayOfLife.BuilderWayOfLife newBuilder() {
        return new WayOfLife().new BuilderWayOfLife();
    }

    public class BuilderWayOfLife {

        private BuilderWayOfLife() {
        }

        public BuilderWayOfLife setId(int id) {
            WayOfLife.this.id = id;
            return this;
        }

        public BuilderWayOfLife setType(String type) {
            WayOfLife.this.type = type;
            return this;
        }

        public WayOfLife build() {
            return WayOfLife.this;
        }
    }
}
