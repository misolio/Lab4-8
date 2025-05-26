package front.ActionGUI;

import back.Toy.Toy;
import front.style.AlertStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import back.Control.ToyDeleter;

import java.util.Optional;

public class DeleteToyDialog implements Command {
    private final ToyDeleter deleter;
    private final Toy toyToDelete;
    private final Boolean simulateConfirmation;
    private final boolean suppressAlerts;
    public DeleteToyDialog(ToyDeleter deleter, Toy toyToDelete) {
        this(deleter, toyToDelete, null, false);
    }

    public DeleteToyDialog(ToyDeleter deleter, Toy toyToDelete, Boolean simulateConfirmation) {
        this(deleter, toyToDelete, simulateConfirmation, true); // <--- true: suppress alerts
    }
    public DeleteToyDialog(ToyDeleter deleter, Toy toyToDelete, Boolean simulateConfirmation, boolean suppressAlerts) {
        this.deleter = deleter;
        this.toyToDelete = toyToDelete;
        this.simulateConfirmation = simulateConfirmation;
        this.suppressAlerts = suppressAlerts;
    }
    @Override
    public void execute() {
        if (!deleter.contains(toyToDelete)) {
            showAlert(Alert.AlertType.WARNING, "Іграшку не знайдено.");
            return;
        }

        boolean confirmed = getUserConfirmation();

        if (confirmed) {
            boolean success = deleter.delete(toyToDelete);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION,
                        "Іграшку \"" + toyToDelete.getName() + "\" успішно видалено.");
            } else {
                showAlert(Alert.AlertType.ERROR,
                        "Помилка при видаленні іграшки \"" + toyToDelete.getName() + "\".");
            }
        }
    }

    protected boolean getUserConfirmation() {
        if (simulateConfirmation != null) {
            return simulateConfirmation;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        AlertStyler.style(confirm);
        confirm.setTitle("Підтвердження видалення");
        confirm.setHeaderText("Ви дійсно хочете видалити іграшку \"" + toyToDelete.getName() + "\"?");
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();
        return result.isPresent() && result.get() == ButtonType.YES;
    }

    private void showAlert(Alert.AlertType type, String message) {
        if (suppressAlerts) return;
        Alert alert = new Alert(type);
        AlertStyler.style(alert);
        alert.setTitle("Повідомлення");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
