package courier;

import java.util.ArrayList;

public class CourierData {

    public static Courier getDefoultCourier() {
        return new Courier("ninja_ninja", "1234", "firstName");
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier("", "1234", "firstName");
    }

    public static Courier getCourierWithoutName() {
        return new Courier("ninja_ninja", "1234", "");
    }

    public static Courier getCourierWithoutPass() {
        return new Courier("ninja_ninja", "", "firstName");
    }

}
