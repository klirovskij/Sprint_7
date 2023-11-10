package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class SucsessLoginCourierTest {
    private static CourierClient courierClient;

    @BeforeClass
    public static void setUp() {
        courierClient = new CourierClient();
    }

    @AfterClass
    public static void tearDown() {
        // Здесь можно удалить курьера или выполнить другие действия по завершению всех тестов.
    }

    @Test
    @DisplayName("Заходим под ранее созданным курьером")
    public void authtorizationCreatedCourier() {
        // Создаем случайного курьера для этого теста
        Courier courier = CourierParams.createdCourier();

        ValidatableResponse validatableResponse =
                courierClient.loginCourier(courier)
                        .assertThat().statusCode(SC_OK)
                        .and().body("id", equalTo(courier.getId()));
    }
}
