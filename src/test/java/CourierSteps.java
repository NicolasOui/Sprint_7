import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step("Создать курьера")
    public Response create(CreateCourier courier) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Авторизовать курьера")
    public Response login(LoginCourier creds) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(creds)
                .when()
                .post("/api/v1/courier/login");
    }
}

