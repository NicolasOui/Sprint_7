import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создать заказ")
    public Response create(CreateOrder order) {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .body(order)
                .when()
                .post(Endpoints.ORDERS);
    }

    @Step("Получить список заказов")
    public Response getOrders() {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .when()
                .get(Endpoints.ORDERS);
    }

    @Step("Отменить заказ")
    public Response cancel(int track) {
        return given()
                .spec(Endpoints.REQ_SPEC)
                .queryParam("track", track)
                .when()
                .put(Endpoints.ORDERS_CANCEL);
    }
}
