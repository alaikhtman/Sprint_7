package testSteps.steps;

import dataProvider.Order;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import testSteps.apiRequests.CreateOrderRequests;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateOrderSteps {
    CreateOrderRequests createOrderRequest = new CreateOrderRequests();

    String randomString = RandomStringUtils.randomAlphabetic(10);
    int randomInt = new Random().nextInt(10);
    String date =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());


    public Response createRandomOrderWithColor(List<String> colour) {
        Order order = new Order(
                randomString,
                randomString,
                randomString,
                randomInt,
                randomString,
                randomInt,
                date,
                randomString,
                colour

        );
        Response response = createOrderRequest.sendPostRequestOrder(order);
        return response;
    }
}
