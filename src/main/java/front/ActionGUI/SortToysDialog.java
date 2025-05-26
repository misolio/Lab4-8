package front.ActionGUI;

import back.Control.ToySorter;
import back.Toy.Toy;
import back.Log.LoggerUtility;

import front.style.DialogStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SortToysDialog implements Command {
    private final ToySorter sorter;
    private Runnable refreshCallback;

    public SortToysDialog(List<Toy> toys) {
        this.sorter = new ToySorter(toys);
    }

    public void setRefreshCallback(Runnable callback) {
        this.refreshCallback = callback;
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Запуск сортування іграшок (через діалог)");

        Map<String, Integer> options = ToySorter.getOptionsMap();
        ChoiceDialog<String> dialog = new ChoiceDialog<>("1. Назва", options.keySet());
        dialog.setTitle("Сортування іграшок");
        dialog.setHeaderText("Оберіть параметр для сортування");
        DialogStyler.style(dialog.getDialogPane());

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return;

        int choice = options.get(result.get());

        if (sorter.sortBy(choice)) {
            LoggerUtility.logInfo("✔ Сортування виконано за параметром: " + result.get());

            if (refreshCallback != null) {
                refreshCallback.run(); // оновлення інтерфейсу
            }

        } else {
            LoggerUtility.logWarning("✖ Некоректний параметр сортування: " + choice);
            showAlert("Помилка", "Невірний вибір параметра.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
