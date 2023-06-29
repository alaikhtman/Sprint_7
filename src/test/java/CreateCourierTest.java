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


public class CreateCourierTest extends CoreAssertions {

    CreateCourierSteps courierSteps = new CreateCourierSteps();
    CoreAssertions assertions = new CoreAssertions();
    DeleteCourierSteps deleteCourierSteps = new DeleteCourierSteps();

    Courier newCourier;


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @After
    public void tearDown() {
        deleteCourierSteps.deleteCourier(newCourier);
    }


    @Test
    @DisplayName("Create courier: courier with all fields")
    @Description("Creation courier with all fields")

    public void createCourierTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierSteps.createSpecificCourier(newCourier);
        assertions.checkResponseStatus(response, 201)
                .checkResponseBody(response, "{\"ok\":true}");

    }

    @Test
    @DisplayName("Create courier: courier with only mandatory fields")
    @Description("Create courier without first name")
    public void createCourierWithoutNameTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                null);
        Response response = courierSteps.createSpecificCourier(newCourier);
        assertions.checkResponseStatus(response, 201)
                .checkResponseBody(response, "{\"ok\":true}");

    }

    @Test
    @DisplayName("Create courier: without mandatory field")
    @Description("Negative test to check that without mandatory field courier is not created")
    public void createCourierWithoutMandatoryFieldImpossibleTest() {
        newCourier = new Courier(
                null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierSteps.createSpecificCourier(newCourier);
        assertions.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "message", "Недостаточно данных для создания учетной записи");

    }

    @Test
    @DisplayName("Create courier: duplicate courier")
    @Description("Negative test to check that with the same login courier is not created")
    public void createDuplicateCourierImpossibleTest() {
        newCourier = new Courier(
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));

        courierSteps.createSpecificCourier(newCourier);

        Courier duplicateCourier = new Courier(
                newCourier.getLogin(),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = courierSteps.createSpecificCourier(duplicateCourier);
        assertions.checkResponseStatus(response, 409)
                .checkResponseBodyFieldValue(response, "message", "Этот логин уже используется. Попробуйте другой.");

        deleteCourierSteps.deleteCourier(duplicateCourier);
    }


}
