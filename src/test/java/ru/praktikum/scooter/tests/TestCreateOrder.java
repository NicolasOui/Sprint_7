package ru.praktikum.scooter.tests;

import org.junit.Test;
import ru.praktikum.scooter.api.OrderSteps;
import ru.praktikum.scooter.model.CreateOrder;
import java.util.List;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

public class TestCreateOrder extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание заказа")
    @io.qameta.allure.Description("Тело ответа содержит track, код ответа 201")
    public void createOrderVerificationResponseBody() {
        CreateOrder order = new CreateOrder(
                "Narouto", "Uchina", "Konoha, 10 apt", "4", "+7 999 000 11 22",
                5, "2026-06-01", "Dont ring the bell", List.of("BLACK")
        );

        orderTrack = orderSteps.create(order)
                .then()
                .statusCode(SC_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");
    }
}
