package courier;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class CourierClient extends Client{

    private final static String CREATE_PATH = "/api/v1/courier";
    private final static String LOGIN_PATH = "/api/v1/courier/login";

    private final static String DELETE_PATH = "/api/v1/courier/";

    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .post(CREATE_PATH)
                .then();
    }

    public ValidatableResponse login(CourierCredentials courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .post(LOGIN_PATH)
                .then();
    }

    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .delete(DELETE_PATH + id)
                .then();
    }
}

