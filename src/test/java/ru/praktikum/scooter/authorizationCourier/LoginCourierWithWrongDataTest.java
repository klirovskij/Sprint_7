package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class LoginCourierWithWrongDataTest {
    private Courier courier;
    private CourierClient courierClient;
    private int statusCode;
    private final String error;

    public LoginCourierWithWrongDataTest(String error, int statusCode, Courier courier) {
        this.error = error;
        this.courier = courier;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getDataForTryingLogin() {
        return new Object[][]{
                {"Учетная запись не найдена", 404, new Courier("КурьерДляТеста", "Qwerty123", "Тестировочный")},
                {"Учетная запись не найдена", 404, new Courier("КурьерДляТестаСОпечаткойАупввв", "Qwerty", "Тестировочный")},
                {"Недостаточно данных для входа", 400, new Courier("КурьерДляТеста", null, "Тестировочный")},
                {"Недостаточно данных для входа", 400, new Courier(null, "Qwerty", "Тестировочный")},
        };
    }

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Пробуем войти под курьером с невалидными или недостаточными данными")
    @Description("Проверяем, что код ответа и сообщение об ошибке соответствуют документации")
    public void tryToAuthCourier() {
        ValidatableResponse validatableResponse = courierClient.loginCourier(courier)
                .assertThat().statusCode(statusCode).log().all()
                .and().body("message", equalTo(error);
    }
}
