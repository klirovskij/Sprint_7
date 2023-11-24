import courier.Courier;
import courier.CourierMethods;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class NewCourierTest {
    private static final String login = "testNewCur1";
    private static final String password = "1234";
    private static final String firstName = "Бородач";
    String id = null;

    @Test
    @DisplayName("Создание нового курьера")
    @Description("")
    public void createCourier() {
        Courier courier = new Courier(login, password, firstName);
        Response response = CourierMethods.createCourier(courier);
        id = CourierMethods.loginCourier(courier).then().extract().path("id").toString();
        response.then().assertThat().statusCode(201)
                .and()
                .body("ok", equalTo(true));
         }

    @Test
    @DisplayName("Запрос с повторяющимся логином")
    @Description("Проверка на отсутствие дублирования при создании курьера.")
    public void doubleCourier() {
        Courier courier = new Courier(login, password, firstName);
        CourierMethods.createCourier(courier);
        Response response = CourierMethods.createCourier(courier);
        id = CourierMethods.loginCourier(courier).then().extract().path("id").toString();
        response.then().assertThat().statusCode(409)
                .and()
                .assertThat().body("message", equalTo("Логин уже используется. "));
    }

    @After
    public void delCourier() {
        CourierMethods.delCourier(id);
    }
}