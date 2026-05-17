package ru.praktikum.scooter.tests;

import org.junit.Test;
import ru.praktikum.scooter.api.OrderSteps;
import ru.praktikum.scooter.model.CreateOrder;
import java.util.List;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class TestListOfOrders extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Список заказов")
    @io.qameta.allure.Description("Тело ответа возвращает список заказов, код ответа 200")
    public void testBodyHasListOfOrders() {

        CreateOrder order = new CreateOrder(
                "Narouto", "Uchina", "Konoha, 10 apt", "4", "+7 999 000 11 22",
                5, "2026-06-01", "Dont ring the bell", List.of("BLACK", "GREY")
        );

        orderTrack = orderSteps.create(order)
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .path("track");

        orderSteps.getOrders()
                .then()
                .statusCode(SC_OK)
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0));
    }
}
