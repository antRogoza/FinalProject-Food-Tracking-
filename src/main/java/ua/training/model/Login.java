package ua.training.model;

public class Login {
    private int id;
    private String email;
    private String password;
    private int idRole;
    private int idClient;

    private Login() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return idRole;
    }

    public int getIdClient() {
        return idClient;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + idRole +
                ", idClient=" + idClient +
                '}';
    }

    public static Login.BuilderLogin newBuilder() {
        return new Login().new BuilderLogin();
    }

    public class BuilderLogin {

        private BuilderLogin() {
        }

        public BuilderLogin setId(int id) {
            Login.this.id = id;
            return this;
        }

        public BuilderLogin setEmail(String email) {
            Login.this.email = email;
            return this;
        }

        public BuilderLogin setPassword(String password) {
            Login.this.password = password;
            return this;
        }

        public BuilderLogin setRole(int idRole) {
            Login.this.idRole = idRole;
            return this;
        }

        public BuilderLogin setIdClient(int idClient) {
            Login.this.idClient = idClient;
            return this;
        }

        public Login build(){
            return Login.this;
        }
    }
}
