package front.style;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AlertStylerTest {

    @BeforeAll
    static void initJFX() throws Exception {
        // Ініціалізація JavaFX-платформи
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @Test
    void testStyleIsApplied() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        final boolean[] result = new boolean[2]; // [0] — стиль CSS, [1] — клас стилю

        Platform.runLater(() -> {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                AlertStyler.style(alert);

                DialogPane pane = alert.getDialogPane();

                result[0] = pane.getStylesheets().stream()
                        .anyMatch(s -> s.contains("/styles/styles.css"));
                result[1] = pane.getStyleClass().contains("custom-dialog");

            } finally {
                latch.countDown();
            }
        });

        // Чекаємо завершення потоку JavaFX
        assertTrue(latch.await(5, TimeUnit.SECONDS), "FX thread timeout");

        assertTrue(result[0], "CSS not applied");
        assertTrue(result[1], "Style class 'custom-dialog' not applied");
    }
}
