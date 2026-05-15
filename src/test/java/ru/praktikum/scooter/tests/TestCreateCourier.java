package ru.praktikum.scooter.tests;

import org.junit.Test;
import ru.praktikum.scooter.api.CourierSteps;
import ru.praktikum.scooter.model.CreateCourier;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class TestCreateCourier extends BaseTest {

    private final CourierSteps steps = new CourierSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Курьер создаётся, код 201")
    public void createCourierTest() {
        CreateCourier courier = new CreateCourier("ninja_scooter_777", "1234", "Satoru");

        courierForDelete = courier;

        steps.create(courier)
                .then()
                .statusCode(SC_CREATED);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Нельзя создать двух одинаковых курьеров")
    public void cannotCreateDoubleCourier() {
        String currentLogin = "superman_praktikum_77";
        CreateCourier courier = new CreateCourier(currentLogin, "12345", "Satoru");

        courierForDelete = courier;

        steps.create(courier)
                .then()
                .statusCode(SC_CREATED);

        steps.create(courier)
                .then()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Успешное создание курьера со всеми обязательными полями возвращает код 201 и ok: true")
    public void createCourierWithAllFieldsAndCheckResponse() {
        String currentLogin = "ivan_praktikum_99";
        CreateCourier courier = new CreateCourier(currentLogin, "password123", "Ivan");

        courierForDelete = courier;

        steps.create(courier)
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнено поле логин при создании курьера")
    public void createCourierWithoutLoginReturns400() {
        CreateCourier courierithoutLogin = new CreateCourier("", "password123", "Ivan");

        steps.create(courierithoutLogin)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнено поле пароль при создании курьера")
    public void createCourierWithoutPasswordReturns400() {
        CreateCourier courierWithoutPassword = new CreateCourier("unique_ninja_1", "", "Satoru");

        steps.create(courierWithoutPassword)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 409 при попытке создать курьера под занятым логином")
    public void createDuplicateCourierReturns409() {
        String currentLogin = "identical_ninja_praktikum";
        CreateCourier courier = new CreateCourier(currentLogin, "1234", "Satoru");

        courierForDelete = courier;

        steps.create(courier);

       CreateCourier secondCourierLogin = new CreateCourier(currentLogin, "12534", "Syatoru");
        steps.create(secondCourierLogin)
                .then()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }
}
