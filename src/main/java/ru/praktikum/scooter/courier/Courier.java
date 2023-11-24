package courier;

import io.restassured.internal.ValidatableResponseOptionsImpl;
import io.restassured.response.ExtractableResponse;
import org.junit.Assert;

import static constants.URL.*;
import static io.restassured.RestAssured.given;

public class Courier {
    private String login;
    private String password;
    private String  firstName;



    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }
}