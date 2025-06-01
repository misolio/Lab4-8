package front.ActionGUI.Sorter;

import front.ActionGUI.SortDialogUI;
import front.style.DialogStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

public class FXSortDialogUI implements SortDialogUI {

    @Override
    public Optional<String> askSortOption(List<String> availableOptions) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(availableOptions.get(0), availableOptions);
        dialog.setTitle("Сортування іграшок");
        dialog.setHeaderText("Оберіть параметр для сортування");
        DialogStyler.style(dialog.getDialogPane());
        return dialog.showAndWait();
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
