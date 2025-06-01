package front.MainGUI.MainWindow;

import static org.junit.jupiter.api.Assertions.*;

import back.Toy.FakeToy;
import back.Toy.Toy;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testfx.api.FxAssert.verifyThat;

public class ToyMenuGUITest extends ApplicationTest {
    private ToyMenuGUI toyMenu;
    private List<Toy> toys;
    private Connection conn = null; // замінити на mock при потребі
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        toys = new ArrayList<>();
        toyMenu = new ToyMenuGUI(toys, conn, stage);
        Scene scene = new Scene(toyMenu, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setupEach() {
        // Налаштування перед кожним тестом
    }

    @Test
    public void testInitialBudgetLabelExists() {
        verifyThat(".label", (label) -> label.isVisible() && label.toString().contains("Бюджет"));
    }

    @Test
    public void testButtonsAreVisible() {
        verifyThat(".button", Button::isVisible);
    }


    @Test
    public void testExitButtonClosesStage() {
        // натискаємо кнопку Вийти та перевіряємо, що вікно закрите
        clickOn("Вийти");
        assertFalse(primaryStage.isShowing());
    }


    @Test
    public void testChangeBudgetDialogAppears() {
        clickOn("Змінити бюджет");
        verifyThat(".dialog-pane", javafx.scene.Node::isVisible);
    }


}
