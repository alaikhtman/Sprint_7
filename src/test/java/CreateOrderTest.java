import assertions.CoreAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testSteps.steps.CancelOrderSteps;
import testSteps.steps.CreateOrderSteps;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    List<String> colour;


    public CreateOrderTest(List<String> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] createOrder() {
        return new Object[][]{
                {Arrays.asList("BACK")},
                {Arrays.asList("WHITE")},
                {Arrays.asList("WHITE", "BLACK")},
                {Arrays.asList()}

        };
    }


    CreateOrderSteps createOrderStep = new CreateOrderSteps();
    CoreAssertions assertions = new CoreAssertions();
    CancelOrderSteps cancelOrderSteps = new CancelOrderSteps();

    String orderTrack;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void tearDown() {
      cancelOrderSteps.cancelOrder1(orderTrack);
    }

    @Test
    @DisplayName("Create order: check color")
    @Description("Order is created with different type of chosen color")
    public void createOrderWithOneColourTest() {

        Response response = createOrderStep.createRandomOrderWithColor(colour);
        assertions.checkResponseStatus(response, 201)
                .checkResponseBodyField(response, "track");
        orderTrack = response.jsonPath().getString("track");
    }

}
