package front.ActionGUI.Deleter;

import front.style.AlertStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class FXUserDialog implements UserDialog {

    @Override
    public boolean confirm(String toyName) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        AlertStyler.style(confirm);
        confirm.setTitle("Підтвердження видалення");
        confirm.setHeaderText("Ви дійсно хочете видалити іграшку \"" + toyName + "\"?");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    @Override
    public void show(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        AlertStyler.style(alert);
        alert.setTitle("Повідомлення");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
