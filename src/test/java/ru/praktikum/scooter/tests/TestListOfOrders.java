package ru.praktikum.scooter.tests;

import org.junit.Test;
import ru.praktikum.scooter.api.OrderSteps;
import ru.praktikum.scooter.model.CreateOrder;

import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.*;

public class TestListOfOrders extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Список заказа")
    @io.qameta.allure.Description("Тело ответа возвращает список заказа")
    public void testBodyHasListOfOrders() {

        CreateOrder order = new CreateOrder(
                "Narouto", "Uchina", "Konoha, 10 apt", "4", "+7 999 000 11 22",
                5, "2026-06-01", "Dont ring the bell", List.of("BLACK", "GREY")
        );

        orderTrack = orderSteps.create(order)
                .then()
                .statusCode(201)
                .extract()
                .path("track");

           orderSteps
                .getOrders()
                .then()
                .assertThat()
                .statusCode(200)
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0));

    }
}


