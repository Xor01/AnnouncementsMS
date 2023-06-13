import io.github.cdimascio.dotenv.Dotenv;
final class DBInfo {

    private static final String URL = Dotenv.load().get("URL");
    private static final String USER = Dotenv.load().get("USER");
    private static final String PASS = Dotenv.load().get("PASS");

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }


}