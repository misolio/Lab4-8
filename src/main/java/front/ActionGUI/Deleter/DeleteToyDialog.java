package front.ActionGUI.Deleter;

import back.Control.ToyDeleter;
import back.Toy.Toy;
import front.ActionGUI.Command;
import back.Log.LoggerUtility;
import javafx.scene.control.Alert;

public class DeleteToyDialog implements Command {
    private final ToyDeleter deleter;
    private final Toy toyToDelete;
    private final UserDialog dialog;

    public DeleteToyDialog(ToyDeleter deleter, Toy toyToDelete, UserDialog dialog) {
        this.deleter = deleter;
        this.toyToDelete = toyToDelete;
        this.dialog = dialog;
        LoggerUtility.logInfo("Ініціалізовано DeleteToyDialog для іграшки: " + toyToDelete.getName());
    }

    public DeleteToyDialog(ToyDeleter deleter, Toy toyToDelete) {
        this(deleter, toyToDelete, new FXUserDialog());
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Спроба видалення іграшки: " + toyToDelete.getName());

        if (!deleter.contains(toyToDelete)) {
            LoggerUtility.logWarning("Іграшку не знайдено в списку: " + toyToDelete.getName());
            dialog.show(Alert.AlertType.WARNING, "Іграшку не знайдено.");
            return;
        }

        boolean confirmed = dialog.confirm(toyToDelete.getName());
        LoggerUtility.logInfo("Підтвердження видалення іграшки \"" + toyToDelete.getName() + "\": " + confirmed);

        if (confirmed) {
            boolean success = deleter.delete(toyToDelete);
            if (success) {
                LoggerUtility.logInfo("✔ Іграшку успішно видалено: " + toyToDelete.getName());
                dialog.show(Alert.AlertType.INFORMATION,
                        "Іграшку \"" + toyToDelete.getName() + "\" успішно видалено.");
            } else {
                dialog.show(Alert.AlertType.ERROR,
                        "Помилка при видаленні іграшки \"" + toyToDelete.getName() + "\".");
            }
        } else {
            LoggerUtility.logInfo("Видалення іграшки скасовано користувачем: " + toyToDelete.getName());
        }
    }
}
