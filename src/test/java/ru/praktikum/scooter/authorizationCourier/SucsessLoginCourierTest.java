package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;

import static org.hamcrest.CoreMatchers.notNullValue;

public class SucsessLoginCourierTest {
    private static CourierClient courierClient;
    private Courier createdCourier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        // Создаем курьера перед каждым тестом
        createdCourier = courierClient.createCourier(new Courier()).extract().as(Courier.class);
    }

    @After
    public void tearDown() {
        // Удаляем курьера после каждого теста
        if (createdCourier != null && createdCourier.getId() != null) {
            courierClient.deleteCourier(Integer.parseInt(createdCourier.getId()));
        }
    }

    @Test
    @DisplayName("Выполняем вход под созданным курьером")
    @Description("Проверяем успешный вход под созданным курьером")
    public void sucsessLoginCourierTest() {
        ValidatableResponse loginResponse = courierClient.loginCourier(createdCourier);
        loginResponse.assertThat().statusCode(200).body("id", notNullValue());
    }
}
