package back.Log;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LoggerUtilityTest {

    @Test
    void testLogInfoDoesNotThrow() {
        assertDoesNotThrow(() -> LoggerUtility.logInfo("Test info message"));
    }

    @Test
    void testLogWarningDoesNotThrow() {
        assertDoesNotThrow(() -> LoggerUtility.logWarning("Test warning message"));
    }

    @Test
    void testLogErrorDoesNotThrow() {
        assertDoesNotThrow(() -> LoggerUtility.logError("Test error message", new RuntimeException("Test exception")));
    }

    @Test
    void testLogDebugDoesNotThrow() {
        assertDoesNotThrow(() -> LoggerUtility.logDebug("Test debug message"));
    }
}
