package ru.praktikum.scooter.authorizationCourier;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.courier.Courier;
import ru.praktikum.scooter.courier.CourierClient;

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
    @DisplayName("Заходим под ранее созданным курьером")
    public void authtorizationCreatedCourier() {
    }
}
