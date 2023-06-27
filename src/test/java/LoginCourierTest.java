import assertions.CoreAssertions;

import dataProvider.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testSteps.steps.CreateCourierSteps;
import testSteps.steps.DeleteCourierSteps;
import testSteps.steps.LoginCourierSteps;

public class LoginCourierTest {

    LoginCourierSteps loginCourierSteps = new LoginCourierSteps();
    CreateCourierSteps courierSteps = new CreateCourierSteps();
    CoreAssertions assertions = new CoreAssertions();
    DeleteCourierSteps deleteCourierSteps = new DeleteCourierSteps();

    Courier newCourier;

    @Before
    public void setUp() {

        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        courierSteps.createSpecificCourier(newCourier);
    }

    @After
    public void tearDown() {
        deleteCourierSteps.deleteCourier(newCourier);
    }

    @Test
    @DisplayName("Login courier: basic test")
    @Description("Basic test for login with created courier")
    public void loginCourierTest() {

        Response response = loginCourierSteps.successfulLoginCourier(newCourier);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyField(response, "id");

    }

    @Test
    @DisplayName("Login courier: without mandatory field - login")
    @Description("Negative test to check that without mandatory login is impossible to login courier")
    public void loginCourierWithoutMandatoryLoginImpossibleTest() {

        Response response = loginCourierSteps.unsuccessfulLoginWithoutLoginCourier(newCourier);
        assertions.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "message", "Недостаточно данных для входа");

    }


    @Test
    @DisplayName("Login courier: incorrect password values")
    @Description("Negative test to check that with the incorrect password values it is impossible to login courier")
    public void loginCourierWithIncorrectPasswordImpossibleTest() {

        Response response = loginCourierSteps.unsuccessfulLoginWithInvalidPasswordCourier(newCourier);
        assertions.checkResponseStatus(response, 404)
                .checkResponseBodyFieldValue(response, "message", "Учетная запись не найдена");

    }

    @Test
    @DisplayName("Login courier: incorrect login values")
    @Description("Negative test to check that with the incorrect login values it is impossible to login courier")
    public void loginCourierWithIncorrectLoginImpossibleTest() {


        Response response = loginCourierSteps.unsuccessfulLoginWithInvalidLoginCourier(newCourier);
        assertions.checkResponseStatus(response, 404)
                .checkResponseBodyFieldValue(response, "message", "Учетная запись не найдена");

    }


}
