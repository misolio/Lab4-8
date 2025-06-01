package front.ActionGUI.Deleter;

import back.Toy.Toy;
import back.Toy.ToyTestImpl;
import back.Control.ToyDeleter;
import javafx.scene.control.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteToyDialogTest {

    private List<Toy> toys;
    private ToyDeleter deleter;
    private Toy toy1;
    private Toy toy2;

    @BeforeEach
    void setUp() {
        toy1 = new ToyTestImpl("Test Toy 1", 100);
        toy2 = new ToyTestImpl("Test Toy 2", 200);
        toys = new ArrayList<>(List.of(toy1, toy2));
        deleter = new ToyDeleter(toys);
    }

    @Test
    void testDeleteConfirmedAndSuccess() {
        UserDialog mockDialog = new UserDialog() {
            @Override
            public boolean confirm(String toyName) {
                return true;
            }

            @Override
            public void show(Alert.AlertType type, String message) {
                assertEquals(Alert.AlertType.INFORMATION, type);
                assertTrue(message.contains("успішно"));
            }
        };

        DeleteToyDialog dialog = new DeleteToyDialog(deleter, toy1, mockDialog);
        dialog.execute();
        assertFalse(toys.contains(toy1));
    }


}
