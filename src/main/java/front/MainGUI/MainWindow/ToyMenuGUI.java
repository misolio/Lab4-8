package front.MainGUI.MainWindow;

import back.Toy.Toy;
import front.ActionGUI.Adding.AddToyDialog;
import front.ActionGUI.Finder.FXToySearchDialogUI;
import front.ActionGUI.Finder.FindToyDialog;
import front.ActionGUI.Sorter.FXSortDialogUI;
import front.ActionGUI.Sorter.SortToysDialog;
import front.style.DialogStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.List;

public class ToyMenuGUI extends BorderPane {
    private final List<Toy> toys;
    private final Connection conn;

    private final ToyBudgetManager budgetManager;
    private final ToyListPanel listPanel;
    private final Button showAllButton;

    public ToyMenuGUI(List<Toy> toys, Connection conn, Stage stage) {
        this.toys = toys;
        this.conn = conn;

        int initialBudget = askInitialBudget();

        this.budgetManager = new ToyBudgetManager(initialBudget);
        ToyCardFactory cardFactory = new ToyCardFactory(toys, this::refreshList);
        this.listPanel = new ToyListPanel(toys, cardFactory);

        VBox leftPanel = new VBox(12,
                budgetManager.getBudgetLabel(),
                createNavButton("Змінити бюджет", budgetManager::showUpdateDialog),
                createNavButton("Додати іграшку", this::handleAdd),
                createNavButton("Сортувати", this::handleSort),
                createNavButton("Знайти", this::handleFind),
                createNavButton("Вийти", stage::close)
        );
        leftPanel.setPadding(new Insets(30));
        leftPanel.setAlignment(Pos.CENTER);
        setLeft(leftPanel);
        leftPanel.getStyleClass().add("menu-panel");

        showAllButton = createNavButton("Показати всі", this::showFullToyRoom);

        ScrollPane scroll = new ScrollPane(listPanel.getPanel());
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setCenter(scroll);

        setPadding(new Insets(20));
        refreshList();
    }

    private int askInitialBudget() {
        TextInputDialog dialog = new TextInputDialog("1000");
        dialog.setTitle("Бюджет");
        dialog.setHeaderText("Вкажіть свій бюджет");
        dialog.setContentText("Сума:");
        DialogStyler.style(dialog.getDialogPane());
        return dialog.showAndWait().map(input -> {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return 1000;
            }
        }).orElse(1000);
    }

    private Button createNavButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void handleAdd() {
        AddToyDialog dialog = new AddToyDialog(toys, budgetManager.getBudget(), conn);
        dialog.execute();
        budgetManager.setBudget(dialog.getBudget());
        refreshList();
    }

    private void handleFind() {
        FindToyDialog findDialog = new FindToyDialog(toys, this::displayFilteredToys, new FXToySearchDialogUI());
        findDialog.execute();
        if (!((VBox) getLeft()).getChildren().contains(showAllButton)) {
            ((VBox) getLeft()).getChildren().add(showAllButton);
        }
    }

    private void handleSort() {
        SortToysDialog command = new SortToysDialog(toys, new FXSortDialogUI());
        command.setRefreshCallback(this::refreshList);
        command.execute();
    }

    public void refreshList() {
        listPanel.refresh(toys);
        ((VBox) getLeft()).getChildren().remove(showAllButton);
    }

    public void showFullToyRoom() {
        refreshList();
    }

    public void displayFilteredToys(List<Toy> filtered) {
        listPanel.refresh(filtered);
    }
}
