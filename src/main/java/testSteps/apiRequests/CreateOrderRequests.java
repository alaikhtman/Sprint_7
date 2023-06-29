package testSteps.apiRequests;


import dataProvider.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class CreateOrderRequests {



    @Step("Send POST request to /api/v1/orders")
    public Response sendPostRequestOrder(Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/v1/orders");
        return response;
    }
}
