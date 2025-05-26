package front.ActionGUI;

import back.Control.ToyFinder;
import back.Toy.Toy;
import back.Log.LoggerUtility;
import front.style.DialogStyler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.*;
import java.util.function.Consumer;

public class FindToyDialog implements Command {
    final ToyFinder finder;
    final Consumer<List<Toy>> displayCallback;

    public FindToyDialog(List<Toy> toys, Consumer<List<Toy>> displayCallback) {
        this.finder = new ToyFinder(toys);
        this.displayCallback = displayCallback;
        LoggerUtility.logInfo("FindToyDialog ініціалізовано.");
    }

    @Override
    public void execute() {
        LoggerUtility.logInfo("Розпочато пошук іграшок (через діалог).");

        try {
            TextInputDialog paramDialog = new TextInputDialog("1");
            paramDialog.setTitle("Параметри пошуку");
            paramDialog.setHeaderText("Скільки параметрів ви хочете використати для пошуку? (1-3)");
            DialogStyler.style(paramDialog.getDialogPane());
            Optional<String> result = paramDialog.showAndWait();
            if (result.isEmpty()) return;

            int numOfParams = Integer.parseInt(result.get());
            if (numOfParams < 1 || numOfParams > 3) {
                showAlert("Помилка", "Кількість параметрів повинна бути від 1 до 3.");
                return;
            }

            finder.reset();
            Map<String, Integer> options = ToyFinder.getOptionsMap();

            for (int i = 0; i < numOfParams; i++) {
                ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("1. Назва", options.keySet());
                choiceDialog.setTitle("Параметр " + (i + 1));
                choiceDialog.setHeaderText("Оберіть параметр для пошуку");
                DialogStyler.style(choiceDialog.getDialogPane());
                Optional<String> choiceResult = choiceDialog.showAndWait();
                if (choiceResult.isEmpty()) continue;

                int paramChoice = options.get(choiceResult.get());

                TextInputDialog valueDialog = new TextInputDialog();
                valueDialog.setTitle("Значення параметра");
                valueDialog.setHeaderText("Введіть значення:");
                DialogStyler.style(valueDialog.getDialogPane());
                Optional<String> valueResult = valueDialog.showAndWait();
                if (valueResult.isEmpty()) continue;

                if (!finder.filterBy(paramChoice, valueResult.get())) {
                    showAlert("Помилка", "Невдалось застосувати фільтр.");
                }
            }

            displayCallback.accept(finder.getFilteredToys());

        } catch (NumberFormatException e) {
            LoggerUtility.logError("Неправильний формат числа.", e);
            showAlert("Помилка", "Введено неправильне значення.");
        } catch (Exception e) {
            LoggerUtility.logError("Помилка під час пошуку іграшок.", e);
            showAlert("Помилка", "Сталася помилка під час пошуку.");
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
