package testSteps.apiRequests;

import dataProvider.Courier;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateCourierRequests {

    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestCourier(Courier courier) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");
        return response;

    }

}
