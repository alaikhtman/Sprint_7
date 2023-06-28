package testSteps.steps;

import io.restassured.response.Response;
import testSteps.apiRequests.CancelOrderRequest;

import java.util.HashMap;
import java.util.Map;

public class CancelOrderSteps {

    CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();

    public Response cancelOrder(String track) {

        Map<String, String> trackInfo = new HashMap<>();
        trackInfo.put("track", track);
        Response response = cancelOrderRequest.sendPutRequestCancelOrder(trackInfo);

        return response;

    }

    public Response cancelOrder1(String track) {

        Response response = cancelOrderRequest.sendPutRequestCancelOrder1(track);
        return response;

    }
}
