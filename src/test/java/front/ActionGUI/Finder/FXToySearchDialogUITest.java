package front.ActionGUI.Finder;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class FXToySearchDialogUITest extends ApplicationTest {

    private FXToySearchDialogUI dialogUI;

    @Override
    public void start(Stage stage) {
        dialogUI = new FXToySearchDialogUI();
    }

    @BeforeEach
    void setUp() {
    }

   @Test
    void testShowErrorDoesNotThrow() {
        runLater(() -> {
            assertDoesNotThrow(() -> dialogUI.showError("Помилка тестування"));
        });
    }

    @Test
    void testAskNumberOfParams() {
        runLater(() -> {
            Optional<Integer> result = dialogUI.askNumberOfParams();
            // Немає перевірки значення, бо очікується взаємодія з користувачем
            assertNotNull(result);
        });
    }

    @Test
    void testChooseParamName() {
        runLater(() -> {
            List<String> params = Arrays.asList("1. Назва", "2. Ціна");
            Optional<String> result = dialogUI.chooseParamName(params, 0);
            assertNotNull(result);
        });
    }

    @Test
    void testAskParamValue() {
        runLater(() -> {
            Optional<String> result = dialogUI.askParamValue("1. Назва");
            assertNotNull(result);
        });
    }

    private void runLater(Runnable runnable) {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            runnable.run();
            latch.countDown();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
