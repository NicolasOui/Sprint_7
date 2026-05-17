package ru.praktikum.scooter.tests;

import org.junit.Before;
import org.junit.Test;
import ru.praktikum.scooter.api.CourierSteps;
import ru.praktikum.scooter.model.CreateCourier;
import ru.praktikum.scooter.model.LoginCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class TestLoginCourier extends BaseTest {

    private final CourierSteps steps = new CourierSteps();
    private final String currentLogin = "ninja_auth_login_main";
    private final String currentPassword = "password123";

    @Before
    public void createCourierBeforeTest() {
        CreateCourier newCourier = new CreateCourier(currentLogin, currentPassword, "Satoru");
        steps.create(newCourier);
        courierForDelete = newCourier;
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Успешная авторизация курьера возвращает код 200 и ID курьера")
    public void courierAutorisationSuccessAndReturnsID() {
        LoginCourier courier = new LoginCourier(currentLogin, currentPassword);
        steps.login(courier)
                .then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 400 и проверка сообщения, если не заполнен логин при авторизации")
    public void courierAutorisationWithoutLoginReturns400() {
        LoginCourier courierWithoutLogin = new LoginCourier("", currentPassword);
        steps.login(courierWithoutLogin)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 404, если логин при авторизации указан неверно")
    public void courierAutorisationLoginFailedReturns404() {
        LoginCourier courierWithWrongLogin = new LoginCourier("completely_wrong_login_ninja", currentPassword);
        steps.login(courierWithWrongLogin)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 404, если пароль при авторизации указан неверно")
    public void courierAutorisationPasswordFailedReturns404() {
        LoginCourier courierWithWrongPassword = new LoginCourier(currentLogin, "wrong_pass_999");
        steps.login(courierWithWrongPassword)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 400 и проверка сообщения, если не заполнен пароль при авторизации")
    public void courierAutorisationWithoutPasswordReturns400() {
        LoginCourier courierWithoutPassword = new LoginCourier(currentLogin, "");
        steps.login(courierWithoutPassword)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 404 при попытке авторизоваться без регистрации")
    public void courierAutorisaionWithoutRegistrationReturns404() {
        LoginCourier courier = new LoginCourier("unregistered_ninja_exclusive", "1234");
        steps.login(courier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
