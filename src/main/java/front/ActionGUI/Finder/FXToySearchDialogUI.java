package front.ActionGUI.Finder;

import front.style.AlertStyler;
import front.style.DialogStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

public class FXToySearchDialogUI implements ToySearchDialogUI {

    @Override
    public Optional<Integer> askNumberOfParams() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Параметри пошуку");
        dialog.setHeaderText("Скільки параметрів ви хочете використати для пошуку? (1-3)");
        DialogStyler.style(dialog.getDialogPane());
        return dialog.showAndWait().map(input -> {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    @Override
    public Optional<String> chooseParamName(List<String> availableParams, int index) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(availableParams.get(0), availableParams);
        dialog.setTitle("Параметр " + (index + 1));
        dialog.setHeaderText("Оберіть параметр для пошуку");
        DialogStyler.style(dialog.getDialogPane());
        return dialog.showAndWait();
    }

    @Override
    public Optional<String> askParamValue(String paramName) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Значення параметра");
        dialog.setHeaderText("Введіть значення для \"" + paramName + "\":");
        DialogStyler.style(dialog.getDialogPane());
        return dialog.showAndWait();
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        AlertStyler.style(alert);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
