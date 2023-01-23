package order;

public class OrderData {


    public static Object[] getDefault(String[] color) {
        return new Object[]
            {"Naruto",
                    "Uchiha",
                    "Konoha, 142 apt.",
                    4,
                    "+7 800 355 35 35",
                    5,
                    "2020-06-06",
                    "Saske, come back to Konoha",
                    color
            };
    }
    public static Object[] getBlackColorOrder() {
        return getDefault(new String[] {"BLACK"});
    }
    public static Object[] getGreyColorOrder() {
        return getDefault(new String[] {"GREY"});
    }
    public static Object[] getTwoColorsOrder() {
        return getDefault(new String[] {"GREY", "BLACK"});
    }
    public static Object[] getNonColorsOrder() {
        return getDefault(new String[] {});
    }
}
