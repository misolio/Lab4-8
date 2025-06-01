package front.ActionGUI.Adding;

import back.Toy.FakeToy;
import back.Toy.Toy;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ToyDialogFXViewTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        AddToyService.ToyDialogFXView view = new AddToyService.ToyDialogFXView();
        Platform.runLater(() -> view.showAlert(Alert.AlertType.INFORMATION, "Test Message"));
    }

    @Test
    void testAlertAppears() {
        verifyThat(".alert", (DialogPane pane) -> pane.getContentText().equals("Test Message"));
    }

    @Test
    void testButtonClick() {
        clickOn("OK"); // або "Yes", "Cancel" тощо
    }
    @Test
    void testPromptToyTypeSelection() {
        Platform.runLater(() -> {
            AddToyService.ToyDialogFXView view = new AddToyService.ToyDialogFXView();
            int result = view.promptToyType();
            // Вручну обери тип у діалозі — або використай Robot API для автоматизації
            System.out.println("Selected type: " + result); // Напр., 1 = Настільні ігри
        });
        sleep(5000); // Час для взаємодії
    }
    @Test
    void testConfirmDialogYes() {
        Platform.runLater(() -> {
            AddToyService.ToyDialogFXView view = new AddToyService.ToyDialogFXView();
            boolean confirmed = view.confirm("Підтвердити дію?");
            System.out.println("Confirmed: " + confirmed); // Очікується true/false
        });
        sleep(5000); // Натисни Yes/No вручну
    }

    @Test
    void testPromptToySelection() {
        Platform.runLater(() -> {
            AddToyService.ToyDialogFXView view = new AddToyService.ToyDialogFXView();
            List<Toy> mockToys = List.of(
                    new FakeToy("Іграшка 1", 50),
                    new FakeToy("Іграшка 2", 75)
            );
            List<Toy> selected = view.promptToySelection(mockToys, 100);
            if (selected != null) {
                System.out.println("Обрано: " + selected.size() + " іграшок.");
            } else {
                System.out.println("Нічого не обрано.");
            }
        });
        sleep(7000); // Дай собі час обрати іграшки
    }
    @Test
    void testPromptToyType_Nastilni() {
        TestableToyDialogFXView view = new TestableToyDialogFXView();
        view.setSimulatedChoice("Настільні ігри");
        assertEquals(1, view.promptToyType());
    }

    @Test
    void testPromptToyType_Lyalky() {
        TestableToyDialogFXView view = new TestableToyDialogFXView();
        view.setSimulatedChoice("Ляльки");
        assertEquals(2, view.promptToyType());
    }

    @Test
    void testPromptToyType_Transport() {
        TestableToyDialogFXView view = new TestableToyDialogFXView();
        view.setSimulatedChoice("Транспорт");
        assertEquals(3, view.promptToyType());
    }

    @Test
    void testPromptToyType_Unknown() {
        TestableToyDialogFXView view = new TestableToyDialogFXView();
        view.setSimulatedChoice("Невідомий тип");
        assertEquals(-1, view.promptToyType());
    }

}
