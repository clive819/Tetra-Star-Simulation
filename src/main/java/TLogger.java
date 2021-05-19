package main.java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TLogger {

    public static TLogger shared = new TLogger();

    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private TLogger() {
    }

    public void log(String message) {
        logger.log(Level.INFO, message);
    }

}
