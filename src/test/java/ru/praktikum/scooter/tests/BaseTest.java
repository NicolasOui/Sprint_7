package ru.praktikum.scooter.tests;

import org.junit.After;
import ru.praktikum.scooter.api.CourierSteps;
import ru.praktikum.scooter.api.OrderSteps;
import ru.praktikum.scooter.model.CreateCourier;
import ru.praktikum.scooter.model.LoginCourier;

public class BaseTest {

    protected CreateCourier courierForDelete;
    protected Integer orderTrack;

    @After
    public void tearDown() {
            if (courierForDelete != null) {
            CourierSteps courierSteps = new CourierSteps();

            LoginCourier loginData = new LoginCourier(courierForDelete.getLogin(), courierForDelete.getPassword());
            Integer courierId = courierSteps.login(loginData)
                    .then()
                    .extract()
                    .path("id");

            if (courierId != null) {
            courierSteps.delete(courierId);
            }
            courierForDelete = null;
        }

        if (orderTrack != null) {
            new OrderSteps().cancel(orderTrack);
            orderTrack = null;
        }
    }
}
