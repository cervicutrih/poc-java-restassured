package transformco.config;

public class ConfigProperties {

    private static final String CLIENT_ID =
            System.getProperty("CLIENT_ID", System.getenv().getOrDefault("CLIENT_ID", ""));

    private static final String CLIENT_PASS =
            System.getProperty("CLIENT_PASS", System.getenv().getOrDefault("CLIENT_PASS", ""));

    private static final String BASE_URI =
            System.getProperty("BASE_URI", System.getenv().getOrDefault("BASE_URI", ""));

//    private static final String BASE_PATH =
//            System.getenv().getOrDefault("BASE_PATH", "/api/xpto");

    private static final String TOKEN_URI =
            System.getProperty("TOKEN_URI", System.getenv().getOrDefault("TOKEN_URI", ""));


    public static String getClientId() {
        return CLIENT_ID;
    }

    public static String getClientPass() {
        return CLIENT_PASS;
    }

    public static String getBaseUri() {
        return BASE_URI;
    }

//    public static String getBasePath() {
//        return BASE_PATH;
//    }

    public static String getTokenUri() {
        return TOKEN_URI;
    }
}
