package ru.praktikum.scooter.tests;

import org.junit.Test;
import ru.praktikum.scooter.api.CourierSteps;
import ru.praktikum.scooter.model.CreateCourier;
import ru.praktikum.scooter.model.LoginCourier;

import static org.hamcrest.Matchers.equalTo;

public class TestCreateCourier extends BaseTest {

    private final CourierSteps steps = new CourierSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Курьер создаётся, код 201") // Описание
    public void createCourierTest() {
        CreateCourier courier = new CreateCourier("ninja_scooter_777", "1234", "Satoru");
         steps.create(courier)
                 .then()
                 .statusCode(201);

        LoginCourier loginData = new LoginCourier("ninja_scooter_777", "1234");
        courierId = steps.login(loginData)
                .then()
                .extract()
                .path("id");
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Нельзя создать двух одинаковых курьеров")
    public void cannotCreateDoubleCourier() {
        String currentLogin = "superman_praktikum_77";
        CreateCourier courier = new CreateCourier(currentLogin, "12345", "Satoru");
        steps.create(courier)
                .then()
                .statusCode(201);

        LoginCourier loginData = new LoginCourier(currentLogin, "12345");
        courierId = steps.login(loginData)
                .then()
                .extract()
                .path("id");

        steps.create(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Передать в ручку всех обязательных полей")
    public void createCourierWithAllFields() {
        String currentLogin = "ivan_praktikum_99";
        CreateCourier courier = new CreateCourier(currentLogin, "password123", "Ivan");
        steps.create(courier)
                .then().statusCode(201);

        LoginCourier loginData = new LoginCourier(currentLogin, "password123");
        courierId = steps.login(loginData)
                .then()
                .extract()
                .path("id");
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Успешный запрос возвращает ok: true")
    public void createCourierTestReponseOk() {
        String currentLogin = "ninja_ok_response_77";
        CreateCourier courier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(courier)
                .then()
                .body("ok", equalTo(true));

        LoginCourier loginData = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(loginData)
                .then()
                .extract()
                .path("id");
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнено поле логин при создании курьера")
    public void createCourierWithoutLoginReturns400() {
            CreateCourier courierithoutLogin = new CreateCourier("", "password123", "Ivan");
            steps.create(courierithoutLogin)
                .then()
                .statusCode(400) // Плохой запрос
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнено поле пароль при создании курьера")
    public void createCourierWithoutPasswordReturns400() {
            CreateCourier courierWithoutPassword = new CreateCourier("unique_ninja_1", "", "Satoru");
            steps.create(courierWithoutPassword)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнено поле имя при создании курьера")
    public void createCourierWithoutFirstNameReturns400() {
        CreateCourier courierWithoutPassword = new CreateCourier("unique_ninja_1", "Satoru", "");
        steps.create(courierWithoutPassword)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Создание курьера")
    @io.qameta.allure.Description("Ошибка 409 при попытке создать курьера под занятым логином")
    public void createDuplicateCourierReturns409() {
        String currentLogin = "identical_ninja_praktikum";
        CreateCourier courier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(courier);

        LoginCourier loginData = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(loginData)
                .then()
                .extract()
                .path("id");

        CreateCourier secondCourierLogin = new CreateCourier("identical_ninja", "12534", "Sуatoru"); //можно не добавлять второй объект
        steps.create(secondCourierLogin)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }
}


