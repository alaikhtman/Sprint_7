import assertions.CoreAssertions;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import testSteps.apiRequests.GetListOfOrdersRequests;

public class GetListOfOrdersTest {

    GetListOfOrdersRequests getListOfOrdersRequests = new GetListOfOrdersRequests();
    CoreAssertions assertions = new CoreAssertions();

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Get list of orders: basic test")
    @Description("Basic test for getting list of orders")
    public void getListOfOrdersTest () {
        Response response = getListOfOrdersRequests.sendGetRequestListOfOrders();
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldNotNull(response, "orders");


    }
}
