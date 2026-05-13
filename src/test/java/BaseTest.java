import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    protected Integer courierId;
    protected Integer orderTrack;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @After
    public void tearDown() {
        if (courierId != null) {
                     io.restassured.RestAssured.given()
                    .header("Content-type", "application/json")
                    .body("{\"id\":\"" + courierId + "\"}")
                    .delete("/api/v1/courier/" + courierId);
            courierId = null;
        }

        if (orderTrack != null) {
            new OrderSteps().cancel(orderTrack);
            orderTrack = null;
        }
    }
}
