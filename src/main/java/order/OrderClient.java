package order;

import courier.Courier;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client{
    private final static String CREATE_PATH = "/api/v1/orders";
    private final static String ORDER_LIST_PATH = "/api/v1/orders"; // но такой эндпоинт не соответствует документации

    private final static String CANCEL_PATH = "/api/v1/orders/cancel";

    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .post(CREATE_PATH)
                .then();
    }
    public ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getSpec())
                .queryParam("track", track)
                .put(CANCEL_PATH)
                .then();
    }
    public ValidatableResponse orderList() {
        return given()
                .spec(getSpec())
                .get(ORDER_LIST_PATH)
                .then();
    }
}
