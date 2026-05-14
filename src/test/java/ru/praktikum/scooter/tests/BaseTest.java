package ru.praktikum.scooter.tests;

import org.junit.After;
import ru.praktikum.scooter.api.CourierSteps;
import ru.praktikum.scooter.api.OrderSteps;

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