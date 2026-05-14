import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Создать курьера")
    public Response create(CreateCourier courier) {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .body(courier)
                .when()
                .post(Endpoints.COURIER);
    }

    @Step("Авторизовать курьера")
    public Response login(LoginCourier creds) {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .body(creds)
                .when()
                .post(Endpoints.COURIER_LOGIN);
    }

    @Step("Удалить курьера")
    public Response delete(int courierId) {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .body(new DeleteCourier(courierId))
                .when()
                .delete(Endpoints.COURIER_DELETE + courierId);
    }
}



