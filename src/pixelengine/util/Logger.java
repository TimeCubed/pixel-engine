package pixelengine.util;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class Logger {
    // ANSI color codes for text colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[91m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    
    private final String loggerName;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public Logger(String name) {
        loggerName = name;
    }

    private String getCurrentTime() {
        return dateFormat.format(new Date());
    }

    public void info(String message) {
        System.out.println(ANSI_GREEN + "[" + getCurrentTime() + "] " + ANSI_BLUE + "[" + loggerName + "/INFO] " + ANSI_RESET + message);
    }

    public void warn(String message) {
        System.out.println(ANSI_GREEN + "[" + getCurrentTime() + "] " + ANSI_BLUE + "[" + loggerName + "/WARN] " + ANSI_YELLOW + message + ANSI_RESET);
    }

    public void error(String message) {
        System.out.println(ANSI_GREEN + "[" + getCurrentTime() + "] " + ANSI_BLUE + "[" + loggerName + "/ERROR] " + ANSI_RED + message + ANSI_RESET);
    }
}
