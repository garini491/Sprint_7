package courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;

public class CourierLoginTest {


    private Courier courier;
    private CourierClient courierClient = new CourierClient();
    private int id;
    @Before
    public void setUp() {
        courier = CourierData.getDefoultCourier();
        courierClient.create(courier);
    }

    @Test
    @DisplayName("Авторизация без пароля")
    @Description("Проверка авторизации курьера без указания пароля")
    public void courierLoginWithoutPassTest() {
        courier.setPassword("");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        String expectedMessage = "Недостаточно данных для входа";
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");
        assertEquals("Статускод не соответствует документации",SC_BAD_REQUEST,statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage,message);
    }
    @Test
    @DisplayName("Авторизация без логина")
    @Description("Проверка авторизации курьера без указания логина")
    public void courierLoginWithoutLoginTest() {
        courier.setLogin("");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        String expectedMessage = "Недостаточно данных для входа";
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");
        assertEquals("Статускод не соответствует документации",SC_BAD_REQUEST,statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage,message);
    }
//
    @Test
    @DisplayName("Корректная авторизация")
    @Description("Проверка авторизации курьера с корректно указанными данными")
    public void courierLoginValidTest() {
        courier = CourierData.getDefoultCourier();
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        loginResponse.assertThat().body("$", hasKey("id"));
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Статускод не соответствует документации",SC_OK,statusCode);
        id = loginResponse.extract().path("id");
    }
    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Проверка авторизации несуществующего курьера")
    public void courierLoginNotValidTest() {
        courier.setLogin("abracadabra");
        courier.setPassword("abracadabra");
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        String expectedMessage = "Учетная запись не найдена";
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");
        assertEquals("Статускод не соответствует документации",SC_NOT_FOUND,statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage,message);
    }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}
