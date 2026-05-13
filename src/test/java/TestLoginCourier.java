import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class TestLoginCourier extends BaseTest {

    private final CourierSteps steps = new CourierSteps();

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Код 200, успешная авторизация курьера")
    public void courierAutorisationReturns200() {
        String currentLogin = "ninja_login_test_777";
        CreateCourier newCourier = new CreateCourier( currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier courier = new LoginCourier( currentLogin, "1234");
        courierId = steps.login(courier)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Успешная авторизация курьера возвращает ID")
    public void courierAutorisationReturnsID() {
        String currentLogin = "ninja_id_test_888";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier courier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(courier)
                .then()
                .body("id", notNullValue())
                .extract()
                .jsonPath()
                .getInt("id");
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнен логин при авторизации")
    public void courierAutorisationWithoutLoginReturns400() {
        String currentLogin = "ninja_no_login_auth_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier courierWithoutLogin = new LoginCourier("", "1234");
        steps.login(courierWithoutLogin)
                .then()
                .statusCode(400);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 409, если логин при авторизации указан неверно")
    public void courierAutorisationLoginFailedReturns404() {
        String currentLogin = "ninja_wrong_login_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier courierWithWrongLogin = new LoginCourier("ninja_77", "1234");
        steps.login(courierWithWrongLogin)
                .then()
                .statusCode(404);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 404, при попытке авторизоваться без регистрации")
    public void courierAutorisaionWithoutAutorisationBeforeReturns404() {
        LoginCourier courier = new LoginCourier("chugasweet", "1234");
        steps.login(courier)
                .then()
                .statusCode(404);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Проверка уведомления об ошибке, если не заполнен логин при авторизации")
    public void courierAutorisationWithoutLoginErrorMessage() {
        String currentLogin = "ninja_no_login_msg_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier courierWithoutLogin = new LoginCourier("", "1234");
        steps.login(courierWithoutLogin)
                .then()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 400, если не заполнен пароль при авторизации")
    public void courierAutorisationWithoutPasswordReturns400() {
        String currentLogin = "ninja_no_password_auth_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier courierWithoutPassword = new LoginCourier(currentLogin, null);
        steps.login(courierWithoutPassword)
                .then()
                .statusCode(400);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Ошибка 404, если пароль при авторизации указан неверно")
    public void courierAutorisationPasswordFailedReturns404() {
        String currentLogin = "ninja_wrong_password_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier courierWithWrongPassword = new LoginCourier(currentLogin, "12345");
        steps.login(courierWithWrongPassword)
                .then()
                .statusCode(404);
    }

    @Test
    @io.qameta.allure.junit4.DisplayName("Логин курьера")
    @io.qameta.allure.Description("Проверка уведомления об ошибке, если не заполнен пароль при авторизации")
    public void courierAutorisationWithoutPasswordErrorMesage() {
        String currentLogin = "ninja_no_pass_msg_777";
        CreateCourier newCourier = new CreateCourier(currentLogin, "1234", "Satoru");
        steps.create(newCourier);

        LoginCourier validCourier = new LoginCourier(currentLogin, "1234");
        courierId = steps.login(validCourier)
                .then()
                .extract()
                .jsonPath()
                .getInt("id");

        LoginCourier ourierWithoutPassword = new LoginCourier(currentLogin, "");
        steps.login(ourierWithoutPassword)
                .then()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
