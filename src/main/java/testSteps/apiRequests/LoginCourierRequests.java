package testSteps.apiRequests;

import dataProvider.Login;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourierRequests {

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostRequestLoginCourier(Login login){
        Response response =given()
                .header("Content-type", "application/json")
                .and()
                .body(login)
                .when()
                .post("/api/v1/courier/login");
        return response;

    }
}
