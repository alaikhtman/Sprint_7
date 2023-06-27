package testSteps.steps;

import dataProvider.Courier;
import dataProvider.Login;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testSteps.apiRequests.DeleteCourierRequest;
import testSteps.apiRequests.LoginCourierRequests;

public class DeleteCourierSteps {

    DeleteCourierRequest deleteCourierRequest = new DeleteCourierRequest();
    LoginCourierRequests loginCourierRequests = new LoginCourierRequests();

    public Response deleteCourier (Courier courier) {

        Login login = new Login(courier.getLogin(), courier.getPassword());
        JsonPath responseLogin = loginCourierRequests.sendPostRequestLoginCourier(login).jsonPath();
        String id = responseLogin.getString("id");
        Response responseDelete = deleteCourierRequest.sendDeleteRequestCourier(id);

        return responseDelete;

    }
}
