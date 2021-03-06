package utils;

/**
 * Created by Dima on 06.07.2016.
 */
public class Logger {

    private static String dateFormat = "dd-MM-yyyy HH:mm:ss";

    public static void info(Object obj) {
        String time = TimeMan.getCurrentDate(dateFormat);
        System.out.println("[INFO] " + time + " " + obj);
    }

    public static void error(Object obj) {
        String time = TimeMan.getCurrentDate(dateFormat);
        System.err.println("[ERROR] " + time + " " + obj);
    }

    public static void warning(Object obj) {
        String time = TimeMan.getCurrentDate(dateFormat);
        System.out.println("[WARNING] " + time + " " + obj);
    }
}

