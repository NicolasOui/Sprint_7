package ru.praktikum.scooter.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.scooter.api.OrderSteps;
import ru.praktikum.scooter.model.CreateOrder;

import java.util.List;

@RunWith(Parameterized.class)
public class TestParameterizedCreateOrderDifferentColors extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();
    private List<String> colors;

        public TestParameterizedCreateOrderDifferentColors(List<String> colors) {
            this.colors = colors;
        }

        @Parameterized.Parameters
        public static Object[][] data() {
            return new Object[][] {
                    { List.of("BLACK") },
                    { List.of("GREY") },
                    { List.of("BLACK", "GREY") },
                    { List.of("") }
            };
        }

        @Test
        @io.qameta.allure.junit4.DisplayName("Создание заказа")
        @io.qameta.allure.Description("Можно указать один из цветов BLACK или GREY; оба цвета; без цвета")
        public void shouldCreateOrderWithDifferentColors() {
            CreateOrder order = new CreateOrder("Naruto", "Uchiha", "Konoha", "4", "+7 999", 5, "2026-06-01", "Hey", colors);
            orderSteps.create(order)
                    .then()
                    .statusCode(201)
                    .extract()
                    .path("track");
        }
}

