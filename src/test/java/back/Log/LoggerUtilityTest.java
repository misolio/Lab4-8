package back.Log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

class LoggerUtilityTest {

    private Logger mockLogger;

    @BeforeEach
    void setup() {
        mockLogger = mock(Logger.class);
    }

    @Test
    void testLogInfo() {
        try (MockedStatic<LoggerFactory> mockedFactory = mockStatic(LoggerFactory.class)) {
            mockedFactory.when(() -> LoggerFactory.getLogger(LoggerUtility.class)).thenReturn(mockLogger);

            LoggerUtility.logInfo("Test info");
            verify(mockLogger).info("Test info");
        }
    }

    @Test
    void testLogWarning() {
        try (MockedStatic<LoggerFactory> mockedFactory = mockStatic(LoggerFactory.class)) {
            mockedFactory.when(() -> LoggerFactory.getLogger(LoggerUtility.class)).thenReturn(mockLogger);

            LoggerUtility.logWarning("Test warn");
            verify(mockLogger).warn("Test warn");
        }
    }

    @Test
    void testLogError() {
        Throwable error = new RuntimeException("Помилка");

        try (MockedStatic<LoggerFactory> mockedFactory = mockStatic(LoggerFactory.class)) {
            mockedFactory.when(() -> LoggerFactory.getLogger(LoggerUtility.class)).thenReturn(mockLogger);

            LoggerUtility.logError("Test error", error);
            verify(mockLogger).error("Test error", error);
        }
    }

    @Test
    void testLogDebug() {
        try (MockedStatic<LoggerFactory> mockedFactory = mockStatic(LoggerFactory.class)) {
            mockedFactory.when(() -> LoggerFactory.getLogger(LoggerUtility.class)).thenReturn(mockLogger);

            LoggerUtility.logDebug("Test debug");
            verify(mockLogger).debug("Test debug");
        }
    }
}
