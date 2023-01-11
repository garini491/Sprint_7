package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private String[] color;


    public OrderCreateTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    private Order order;
    private OrderClient orderClient;
    private int track;
    @Parameterized.Parameters
    public static Object[][] requestParams() {
        return new Object[][] {
                    OrderData.getGreyColorOrder(),
                    OrderData.getBlackColorOrder(),
                    OrderData.getTwoColorOrder(),
                    OrderData.getNonColorOrder(),
        };
    }
    @Before
    public void setUp() {
        order = new Order(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);
       orderClient = new OrderClient();
    }
    @Test
    @DisplayName("Проверка создания заказа")
    public void orderCreateTest() {
        ValidatableResponse response = orderClient.createOrder(order);
        response.assertThat().body("$", hasKey("track"));
        track = response.extract().body().path("track");
        System.out.println(track);
    }

    @After
    public void tearDown() {
        orderClient.cancelOrder(track);
    }

}