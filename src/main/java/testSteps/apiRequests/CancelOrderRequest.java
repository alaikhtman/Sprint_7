package testSteps.apiRequests;

import dataProvider.Courier;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CancelOrderRequest {

    @Step("Send PUT request to /api/v1/orders/cancel")
    public Response sendPutRequestCancelOrder(Map<String, String> trackInfo) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(trackInfo)
                .when()
                .put("/api/v1/orders/cancel");
        return response;

    }

    @Step("Send PUT request to /api/v1/orders/cancel")
    public Response sendPutRequestCancelOrder1(String track) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .queryParam("track", track)
                .put("/api/v1/orders/cancel" );
        return response;

    }
}
