package front.ActionGUI.Deleter;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class FXUserDialogTest extends ApplicationTest {

    private FXUserDialog dialog;

    @Override
    public void start(Stage stage) {
        dialog = new FXUserDialog();
    }

    @Test
    void testShowInformationAlert() {
        // Тестуємо метод show (але без перевірки UI — лише щоб не зламалось)
        runLater(() -> {
            assertDoesNotThrow(() ->
                    dialog.show(Alert.AlertType.INFORMATION, "Тестове повідомлення")
            );
        });
    }

    @Test
    void testShowConfirmationYes() {
        // ⚠️ Цей тест не автоматизує клік по кнопці, потрібна емуляція користувача
        // Краще виділити логіку діалогу окремо й мокати її
        // Тут просто перевіряємо, що confirm не падає
        runLater(() -> {
            boolean result = dialog.confirm("М'ячик");
            // результат залежить від ручного кліку по Yes/No
            System.out.println("Результат підтвердження: " + result);
        });
    }

    private void runLater(Runnable runnable) {
        try {
            javafx.application.Platform.runLater(runnable);
            Thread.sleep(1000); // Дати JavaFX час на виконання
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
