package front.MainGUI;

import static org.junit.jupiter.api.Assertions.*;

import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class WelcomeWindowTest extends ApplicationTest {

    private boolean started;

    @BeforeEach
    void setUp() {
        started = false;
    }

    @Override
    public void start(Stage stage) {
        WelcomeWindow window = new WelcomeWindow(false);
        window.show(stage, () -> started = true);
    }

    @Test
    public void testWelcomeWindowElements() {
        Label greeting = lookup(".welcome-label").queryAs(Label.class);
        Button startButton = lookup(".welcome-button").queryAs(Button.class);

        assertNotNull(greeting);
        assertEquals("Вітаємо у кімнаті іграшок!", greeting.getText());

        assertNotNull(startButton);
        assertEquals("Почати", startButton.getText());
    }

    @Test
    public void testStartButtonClosesWindowAndRunsAction() {
        clickOn(".welcome-button");

        // Перевірка, що дія виконана
        assertTrue(started);

        // Перевірка, що всі модальні вікна закриті
        boolean modalExists = listTargetWindows().stream().anyMatch(Window::isShowing);
        assertFalse(modalExists);
    }
    @Test
    public void testDefaultConstructorDoesNotThrow() {
        assertDoesNotThrow(() -> {
            WelcomeWindow window = new WelcomeWindow();
            assertNotNull(window);
        });
    }
}
