package ru.praktikum.scooter.courier;

public class LoginCourier {
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;

    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
