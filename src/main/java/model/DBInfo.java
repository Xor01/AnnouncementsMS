package model;

import io.github.cdimascio.dotenv.Dotenv;
final class DBInfo {

    private static final String URL = load("URL");
    private static final String USER = load("USER");
    private static final String PASS = load("PASS");

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASS() {
        return PASS;
    }

    private static String load(String s) {
        try{
            return Dotenv.load().get(s);
        }
        catch (Exception e){
            return "";
        }
    }
}