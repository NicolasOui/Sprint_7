import org.junit.After;

public class BaseTest {

    protected Integer courierId;
    protected Integer orderTrack;

    @After
    public void tearDown() {
        if (courierId != null) {
            new CourierSteps().delete(courierId);
            courierId = null;
        }

        if (orderTrack != null) {
            new OrderSteps().cancel(orderTrack);
            orderTrack = null;
        }
    }
}