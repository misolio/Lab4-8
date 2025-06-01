package front.MainGUI.MainWindow;

import back.Toy.FakeToy;
import back.Toy.Toy;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class ToyCardFactoryTest extends ApplicationTest {

    private List<Toy> toys;
    private AtomicBoolean deleteTriggered;
    private ToyCardFactory factory;

    @Override
    public void start(Stage stage) {
        // Required by TestFX to bootstrap JavaFX platform
    }

    @BeforeEach
    public void setUp() {
        toys = new ArrayList<>();
        toys.add(new FakeToy("М'ячик", 150));
        deleteTriggered = new AtomicBoolean(false);
        factory = new ToyCardFactory(toys, () -> deleteTriggered.set(true));
    }

    @Test
    public void testCardContainsCorrectUIElements() {
        Toy toy = toys.get(0);
        HBox card = factory.createCard(toy);

        assertEquals(4, card.getChildren().size());

        Node nameNode = card.getChildren().get(0);
        Node priceNode = card.getChildren().get(1);
        Node detailsBtn = card.getChildren().get(2);
        Node deleteBtn = card.getChildren().get(3);

        assertTrue(nameNode instanceof javafx.scene.control.Label);
        assertTrue(priceNode instanceof javafx.scene.control.Label);
        assertTrue(detailsBtn instanceof Button);
        assertTrue(deleteBtn instanceof Button);

        assertEquals("Назва: М'ячик", ((javafx.scene.control.Label) nameNode).getText());
        assertEquals("Ціна: ₴150", ((javafx.scene.control.Label) priceNode).getText());
    }

    @Test
    public void testDeleteButtonTriggersCallback() {
        Toy toy = toys.get(0);
        HBox card = factory.createCard(toy);
        Button deleteBtn = (Button) card.getChildren().get(3);

        // Симулюємо клік (у JavaFX UI-потоку)
        interact(() -> deleteBtn.fire());

        assertTrue(deleteTriggered.get(), "Повинна виконатись дія onToyDeleted");
    }

    @Test
    public void testShowToyDetailsDoesNotThrow() throws Exception {
        List<Toy> toys = new ArrayList<>();
        Toy toy = new FakeToy("М'яч", 150);  // простий тестовий об'єкт
        ToyCardFactory factory = new ToyCardFactory(toys, () -> {});

        Method method = ToyCardFactory.class.getDeclaredMethod("showToyDetails", Toy.class);
        method.setAccessible(true);

        // Запускаємо JavaFX-код на FX Application Thread
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> method.invoke(factory, toy));
        });

        // Даємо JavaFX час відпрацювати
        Thread.sleep(1000);
    }
}
