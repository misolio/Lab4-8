package front.ActionGUI.Deleter;

import javafx.scene.control.Alert;

public interface UserDialog {
    boolean confirm(String toyName);
    void show(Alert.AlertType type, String message);
}
