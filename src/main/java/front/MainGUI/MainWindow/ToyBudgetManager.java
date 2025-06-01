package front.MainGUI.MainWindow;

import javafx.scene.control.Label;
import front.style.DialogStyler;
import javafx.scene.control.TextInputDialog;

public class ToyBudgetManager {
    private int budget;
    private final Label budgetLabel;

    public ToyBudgetManager(int initialBudget) {
        this.budget = initialBudget;
        this.budgetLabel = new Label("Бюджет: ₴" + budget);
        budgetLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
    }

    public void showUpdateDialog() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(budget));
        dialog.setTitle("Оновити бюджет");
        dialog.setHeaderText("Введіть новий бюджет:");
        dialog.setContentText("Сума:");
        DialogStyler.style(dialog.getDialogPane());

        dialog.showAndWait().ifPresent(input -> {
            try {
                int newBudget = Integer.parseInt(input.trim());
                if (newBudget >= 0) {
                    budget = newBudget;
                    updateLabel();
                }
            } catch (NumberFormatException ignored) {}
        });
    }

    private void updateLabel() {
        budgetLabel.setText("Бюджет: ₴" + budget);
    }

    public Label getBudgetLabel() {
        return budgetLabel;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
        updateLabel();
    }
    // Додати до ToyBudgetManager:
    public void simulateBudgetInput(String input) {
        try {
            int newBudget = Integer.parseInt(input.trim());
            if (newBudget >= 0) {
                budget = newBudget;
                updateLabel();
            }
        } catch (NumberFormatException ignored) {}
    }

}
