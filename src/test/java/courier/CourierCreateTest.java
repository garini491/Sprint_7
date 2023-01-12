package courier;

import io.qameta.allure.junit4.DisplayName;


import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private int id;
    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Проверка создания курьера с корректно заполненными данными")
    public void courierCreateValidTest() {
        courier = CourierData.getDefoultCourier();
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id");
        int statusCode = response.extract().statusCode();
        boolean ok = response.extract().path("ok");
        assertEquals(SC_CREATED,statusCode);
        assertTrue(ok);

    }


    @Test
    @DisplayName("Проверка создания двух курьеров с одинаковыми данными")
    public void courierDoubleCreateTest() {
        courier = CourierData.getDefoultCourier();
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        id = loginResponse.extract().path("id");
        int statusCode = response.extract().statusCode();
        String expectedMessage = "Этот логин уже используется.";
        String message = response.extract().path("message");
        assertEquals("Код ответа не соответствует документации",SC_CONFLICT, statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage, message);
    }
    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void courierCreateWithoutLoginTest() {
        courier = CourierData.getCourierWithoutLogin();
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String message = response.extract().path("message");
        assertEquals("Код ответа не соответствует документации",SC_BAD_REQUEST, statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage, message);
    }

    @Test
    @DisplayName("Проверка создания курьера без пароля")
    public void courierCreateWithoutPassTest() {
        courier = CourierData.getCourierWithoutPass();
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String message = response.extract().path("message");
        assertEquals("Код ответа не соответствует документации",SC_BAD_REQUEST, statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage, message);
    }
    @Test
    @DisplayName("Проверка создания курьера без имени")
    public void courierCreateWithoutNameTest() {
        courier = CourierData.getCourierWithoutName();
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        String expectedMessage = "Недостаточно данных для создания учетной записи";
        String message = response.extract().path("message");
        assertEquals("Код ответа не соответствует документации",SC_BAD_REQUEST, statusCode);
        assertEquals("Текст сообщения не соответствует документации",expectedMessage, message);
    }
    @After
    public void tearDown() {
        courierClient.delete(id);
    }
}