package front.MainGUI.MainWindow;

import back.Toy.FakeToy;
import back.Toy.Toy;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToyListPanelTest extends ApplicationTest {

    private List<Toy> toyList;
    private ToyListPanel toyListPanel;

    @Override
    public void start(Stage stage) {
        // запускає JavaFX середовище
    }

    @BeforeEach
    public void setUp() {
        toyList = new ArrayList<>();
        ToyCardFactory dummyFactory = new ToyCardFactory(toyList, () -> {});
        toyListPanel = new ToyListPanel(toyList, dummyFactory);
    }

    @Test
    public void testEmptyStateInitially() {
        VBox panel = toyListPanel.getPanel();

        assertEquals(1, panel.getChildren().size(), "Має бути одне повідомлення");
        Node child = panel.getChildren().get(0);
        assertTrue(child instanceof Label);
        assertEquals("Кімната порожня", ((Label) child).getText());
    }

    @Test
    public void testRefreshWithToys() {
        toyList.add(new FakeToy("Лего", 300));
        toyList.add(new FakeToy("Плюшевий ведмідь", 200));

        ToyCardFactory testFactory = new ToyCardFactory(toyList, () -> {});
        toyListPanel = new ToyListPanel(toyList, testFactory);
        toyListPanel.refresh(toyList);

        VBox panel = toyListPanel.getPanel();
        assertEquals(2, panel.getChildren().size(), "Має бути 2 картки іграшок");

        for (Node node : panel.getChildren()) {
            assertTrue(node instanceof HBox, "Очікується картка у вигляді HBox");
        }
    }

    @Test
    public void testRefreshBackToEmpty() {
        toyList.add(new FakeToy("Робот", 450));
        ToyCardFactory testFactory = new ToyCardFactory(toyList, () -> {});
        toyListPanel = new ToyListPanel(toyList, testFactory);
        toyListPanel.refresh(toyList);

        // очищення списку
        toyList.clear();
        toyListPanel.refresh(toyList);

        VBox panel = toyListPanel.getPanel();
        assertEquals(1, panel.getChildren().size());
        assertTrue(panel.getChildren().get(0) instanceof Label);
        assertEquals("Кімната порожня", ((Label) panel.getChildren().get(0)).getText());
    }
}
