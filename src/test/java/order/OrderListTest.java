package order;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {

    private OrderClient orderClient;
    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }
    @Test
    @Description("Проверка наличия orders в ответе на запрос получения информации по заказам")
    public void orderListHasOrders() {
        ValidatableResponse response = orderClient.orderList();
        response.assertThat().body("$",hasKey("orders")).and().body("orders",notNullValue()).and().statusCode(200);
    }
}