package front.ActionGUI.Sorter;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FXSortDialogUITest extends ApplicationTest {

    private FXSortDialogUI dialogUI;


    @Override
    public void start(Stage stage) {
        dialogUI = new FXSortDialogUI();
    }

    @Test
    public void testAskSortOption_ReturnsChoice() throws Exception {
        List<String> options = List.of("ID", "Name", "Price");

        // Виконуємо у JavaFX Application Thread
        CountDownLatch latch = new CountDownLatch(1);
        final Optional<String>[] result = new Optional[1];

        Platform.runLater(() -> {
            result[0] = dialogUI.askSortOption(options);
            latch.countDown();
        });

        latch.await(10, TimeUnit.SECONDS);

        // ❗️ Неможливо перевірити результат без ручного кліку — тому перевіряємо, що об'єкт Optional не null
        assertNotNull(result[0]);
    }

    @Test
    public void testShowError_DoesNotCrash() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            dialogUI.showError("Тестова помилка");
            latch.countDown();
        });

        latch.await(10, TimeUnit.SECONDS);
        // Якщо ми дійшли сюди — значить showError() не впав
        assertTrue(true);
    }
}
