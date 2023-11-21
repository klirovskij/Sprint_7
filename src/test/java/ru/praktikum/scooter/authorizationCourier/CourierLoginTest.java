package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;
import ru.praktikum.scooter.courier.CourierParams;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierLoginTest {
    CourierClient courierClient = new CourierClient();
    Courier courier = CourierParams.randomCourier();
    private static Integer courierId;

    @Before
    public void setUp(){
        ValidatableResponse validatableResponse = courierClient.createCourier(courier);
    }

    @Test
    @DisplayName("Выполняем вход под созданным курьером ")
    public void sucsessLoginCourierTest(){

        ValidatableResponse loginResponse = courierClient.loginCourier(courier);
        courierId = loginResponse.extract().path("id");
        loginResponse.assertThat().statusCode(SC_OK).body("id", notNullValue());

    }
    After
    public void clear() {
        try {
            courierClient.deleteCourier(courierId);
        } catch (Exception e) {
            fail("Failed to clean up resources: " + e.getMessage());
        }
    }

}
