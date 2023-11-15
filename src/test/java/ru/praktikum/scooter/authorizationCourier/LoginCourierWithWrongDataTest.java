package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginCourierWithWrongDataTest {
    private CourierClient courierClient;
    private String errorMessageActual;
    private static final String errorMessageExpected = "Недостаточно данных для создания учетной записи";

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Пробуем войти под курьером с невалидными или недостаточными данными")
    @Description("Проверяем, что код ответа и сообщение об ошибке соответствуют документации")
    public void tryToAuthCourier() {
        // Пример использования константы errorMessageExpected
        ValidatableResponse validatableResponse = courierClient.loginCourier(new Courier())
                .assertThat().statusCode(400).log().all()
                .and().body("message", equalTo(errorMessageExpected));
    }
}
