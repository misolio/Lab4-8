package back.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtility {
    public static void logInfo(String message) {
        Logger logger = LoggerFactory.getLogger(LoggerUtility.class);
        logger.info(message);
    }

    public static void logWarning(String message) {
        Logger logger = LoggerFactory.getLogger(LoggerUtility.class);
        logger.warn(message);
    }

    public static void logError(String message, Throwable t) {
        Logger logger = LoggerFactory.getLogger(LoggerUtility.class);
        logger.error(message, t);
    }

    public static void logDebug(String message) {
        Logger logger = LoggerFactory.getLogger(LoggerUtility.class);
        logger.debug(message);
    }
}
