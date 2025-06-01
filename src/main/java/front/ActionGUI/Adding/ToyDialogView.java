package front.ActionGUI.Adding;

import back.Toy.Toy;
import javafx.scene.control.Alert;

import java.util.List;

public interface ToyDialogView {
    int promptToyType();
    List<Toy> promptToySelection(List<Toy> availableToys, int budget);
    boolean confirm(String message);
    void showAlert(Alert.AlertType type, String message);
}
