package ua.training.model;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int height;
    private int idCriterion;

    private Client() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getHeight() {
        return height;
    }

    public int getIdCriterion() {
        return idCriterion;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height=" + height +
                ", idCriterion=" + idCriterion +
                '}';
    }

    public static Client.BuilderClient newBuilder() {
        return new Client().new BuilderClient();
    }

    public class BuilderClient {

        private BuilderClient() {
        }

        public BuilderClient setId(int id) {
            Client.this.id = id;
            return this;
        }

        public BuilderClient setFirstName(String firstName) {
            Client.this.firstName = firstName;
            return this;
        }

        public BuilderClient setLastName(String lastName) {
            Client.this.lastName = lastName;
            return this;
        }

        public BuilderClient setHeight(int height) {
            Client.this.height = height;
            return this;
        }

        public BuilderClient setIdCriterion(int idCriterion) {
            Client.this.idCriterion = idCriterion;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }
}
