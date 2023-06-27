package dataProvider;

public class Login {

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Login() {

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String login;
    private String password;
}
