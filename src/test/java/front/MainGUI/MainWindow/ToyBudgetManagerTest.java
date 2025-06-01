package front.MainGUI.MainWindow;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ToyBudgetManagerTest extends ApplicationTest {

    private ToyBudgetManager manager;

    @Override
    public void start(Stage stage) {
        // JavaFX needs a stage even if not shown
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        manager = new ToyBudgetManager(100);
    }

    @Test
    public void testInitialBudgetLabel() {
        assertEquals("Бюджет: ₴100", manager.getBudgetLabel().getText());
    }

    @Test
    public void testSetBudgetUpdatesLabel() {
        manager.setBudget(500);
        assertEquals("Бюджет: ₴500", manager.getBudgetLabel().getText());
    }

    @Test
    public void testShowUpdateDialogSimulatedInput() {
        // Імітація діалогу — перенесемо логіку в окремий метод, який можна протестувати
        manager.simulateBudgetInput("250");
        assertEquals(250, manager.getBudget());
        assertEquals("Бюджет: ₴250", manager.getBudgetLabel().getText());
    }
}
