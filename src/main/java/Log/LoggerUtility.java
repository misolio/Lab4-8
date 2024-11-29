package Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtility {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtility.class);

    private LoggerUtility() {
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warn(message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }
}
