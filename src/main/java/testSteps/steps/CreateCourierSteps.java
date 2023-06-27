package testSteps.steps;

import dataProvider.Courier;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import testSteps.apiRequests.CreateCourierRequests;

public class CreateCourierSteps {

    CreateCourierRequests createCourierRequests = new CreateCourierRequests();


    public Response createSpecificCourier(Courier courier) {
        Response response = createCourierRequests.sendPostRequestCourier(courier);
        return response;
    }


}
