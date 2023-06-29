package testSteps.apiRequests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetListOfOrdersRequests {

    @Step("Send GET request to /api/v1/orders")
    public Response sendGetRequestListOfOrders(){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .get("/api/v1/orders");
        return response;
    }
}
