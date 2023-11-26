import courier.Courier;
import courier.CourierMethods;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {
    private static final String login = "testlog";
    private static final String nonexistentLogin = "nullcourier";
    private static final String password = "1234";
    private static final String nonexistentPassword = "nullpass";
    String id;

    @Test
    @DisplayName("Успешный логин")
    @Description("Созданный курьер успешно авторизуется")
    public void createCourier() {
        Courier courier = new Courier(login, password);
        CourierMethods.createCourier(courier);
        id = CourierMethods.loginCourier(courier).then().extract().path("id").toString();
        Response response = CourierMethods.loginCourier(courier);
        response.then().assertThat().statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Запрос без логина")
    @Description("Курьер создается без логина")
    public void loginCourierWithoutLogin() {
        Courier courier = new Courier("", password);
        Response response = CourierMethods.loginCourier(courier);
        response.then().assertThat().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Запрос без пароля")
    @Description("Курьер создается без пароля")
    public void loginCourierWithoutPassword() {
        Courier courier = new Courier(login, "");
        Response response = CourierMethods.loginCourier(courier);
        response.then().assertThat().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Запрос с несуществующим логином")
    @Description("Логин курьера в системе отсутствует")
    public void loginCourierNotCorrectLogin() {
        Courier courier = new Courier(nonexistentLogin, password);
        Response response = CourierMethods.loginCourier(courier);
        response.then().assertThat().statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Запрос с несуществующим паролем")
    @Description("Пароль не соответсвует созданному курьеру")
    public void loginCourierNotCorrectPassword() {
        Courier courier = new Courier(login, nonexistentPassword);
        Response response = CourierMethods.loginCourier(courier);
        response.then().assertThat().statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void delCourier() {
        if (id != null){
        CourierMethods.delCourier(id);}
    }
}