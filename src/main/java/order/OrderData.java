package order;

public class OrderData {
    public static Object[] getGreyColorOrder() {
        return new Object[] {"Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                new String[] {"GREY"}};
    }
    public static Object[] getBlackColorOrder() {
        return new Object[] {"Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                new String[] {"BLACK"}};
    }
    public static Object[] getTwoColorOrder() {
        return new Object[] {"Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                new String[] {"GREY","BLACK"}};
    }
    public static Object[] getNonColorOrder() {
        return new Object[] {"Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                new String[] {}};
    }
}
