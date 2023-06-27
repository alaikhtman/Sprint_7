package testSteps.steps;

import dataProvider.Courier;
import dataProvider.Login;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import testSteps.apiRequests.LoginCourierRequests;

public class LoginCourierSteps {

    LoginCourierRequests loginCourierRequests = new LoginCourierRequests();

    public Response successfulLoginCourier(Courier courier) {
        Login login = new Login(courier.getLogin(), courier.getPassword());
        Response response = loginCourierRequests.sendPostRequestLoginCourier(login);
        return response;
    }

    public Response unsuccessfulLoginWithoutLoginCourier(Courier courier) {
        Login login = new Login(null, courier.getPassword());
        Response response = loginCourierRequests.sendPostRequestLoginCourier(login);
        return response;
    }

    public Response unsuccessfulLoginWithoutPasswordCourier(Courier courier) {
        Login login = new Login(courier.getLogin(), null);
        Response response = loginCourierRequests.sendPostRequestLoginCourier(login);
        return response;
    }

    public Response unsuccessfulLoginWithInvalidLoginCourier(Courier courier) {
        Login login = new Login(courier.getLogin(), RandomStringUtils.randomAlphabetic(10));
        Response response = loginCourierRequests.sendPostRequestLoginCourier(login);
        return response;
    }

    public Response unsuccessfulLoginWithInvalidPasswordCourier(Courier courier) {
        Login login = new Login(RandomStringUtils.randomAlphabetic(10), courier.getPassword());
        Response response = loginCourierRequests.sendPostRequestLoginCourier(login);
        return response;
    }
}
