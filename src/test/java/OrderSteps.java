import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Создать заказ")
    public Response create(CreateOrder order) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получить список заказов")
    public Response getOrders() {
        return given()
                .filter(new AllureRestAssured())
                .get("/api/v1/orders");
    }

    @Step("Отменить заказ")
    public Response cancel(int track) {
        return given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .queryParam("track", track)
                .when()
                .put("/api/v1/orders/cancel");
    }
}