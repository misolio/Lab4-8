package front.MainGUI;

import back.Toy.Toy;
import back.Control.ToyDeleter;
import front.ActionGUI.*;
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
    private int budget;
    private VBox toyDisplayPanel;
    private Label emptyLabel;
    private Label budgetLabel;
    private Button showAllButton;


    public ToyMenuGUI(List<Toy> toys, Connection conn, Stage stage) {
        this.toys = toys;
        this.conn = conn;

        // === ЗАПИТ БЮДЖЕТУ ===
        TextInputDialog dialog = new TextInputDialog("1000");
        dialog.setTitle("Бюджет");
        dialog.setHeaderText("Вкажіть свій бюджет");
        dialog.setContentText("Сума:");
        DialogStyler.style(dialog.getDialogPane());
        dialog.showAndWait().ifPresent(input -> {
            try {
                budget = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                budget = 1000;
            }
        });

        // === ЛІВА ПАНЕЛЬ НАВІГАЦІЇ ===
        VBox leftPanel = new VBox(12);
        leftPanel.setPadding(new Insets(30));
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.getStyleClass().add("menu-panel");

        budgetLabel = new Label("Бюджет: ₴" + budget);
        budgetLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        leftPanel.getChildren().add(budgetLabel);

        Button updateBudgetButton = createNavButton("Змінити бюджет", this::handleUpdateBudget);
        Button addButton = createNavButton("Додати іграшку", this::handleAdd);
        Button sortButton = createNavButton("Сортувати", this::handleSort);
        Button findButton = createNavButton("Знайти", this::handleFind);
        Button exitButton = createNavButton("Вийти", stage::close);
        showAllButton = createNavButton("Показати всі", this::showFullToyRoom);


        leftPanel.getChildren().addAll( updateBudgetButton,addButton, sortButton, findButton, exitButton);
        setLeft(leftPanel);

        // === ЦЕНТРАЛЬНА ПАНЕЛЬ ===
        toyDisplayPanel = new VBox(20);
        toyDisplayPanel.setPadding(new Insets(20));
        toyDisplayPanel.setAlignment(Pos.TOP_CENTER);

        emptyLabel = new Label("Кімната порожня");
        emptyLabel.setStyle("-fx-text-fill: #888; -fx-font-style: italic;");
        toyDisplayPanel.getChildren().add(emptyLabel);

        ScrollPane scrollPane = new ScrollPane(toyDisplayPanel);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.getStyleClass().add("scroll-pane");

        setCenter(scrollPane);
        setPadding(new Insets(20));

        refreshToyList();
    }

    private Button createNavButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setPrefWidth(Region.USE_COMPUTED_SIZE); // або просто не став нічого
        button.setOnAction(e -> action.run());

        return button;
    }
    private void handleFind() {
        FindToyDialog findToy = new FindToyDialog(toys, this::updateDisplayedToys);
        findToy.execute();

        if (!((VBox) getLeft()).getChildren().contains(showAllButton)) {
            ((VBox) getLeft()).getChildren().add(showAllButton);
        }
    }

    public void showFullToyRoom() {
        refreshToyList();
        ((VBox) getLeft()).getChildren().remove(showAllButton); // Сховати кнопку
    }


    private void refreshToyList() {
        toyDisplayPanel.getChildren().clear();

        if (toys.isEmpty()) {
            toyDisplayPanel.getChildren().add(emptyLabel);
        } else {
            for (Toy toy : toys) {
                toyDisplayPanel.getChildren().add(createToyCard(toy));
            }
        }
    }

    private HBox createToyCard(Toy toy) {
        Label nameLabel = new Label("Назва: " + toy.getName());
        Label priceLabel = new Label("Ціна: ₴" + toy.getPrice());

        Button detailsBtn = new Button("Детальніше");
        detailsBtn.setOnAction(e -> showToyDetails(toy));

        Button deleteBtn = new Button("Видалити");
        deleteBtn.setOnAction(e -> {
            ToyDeleter deleter = new ToyDeleter(toys);
            DeleteToyDialog deleteCommand = new DeleteToyDialog(deleter, toy);
            deleteCommand.execute();
            refreshToyList();
        });

        HBox card = new HBox(20, nameLabel, priceLabel, detailsBtn, deleteBtn);
        card.getStyleClass().add("toy-card");
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(10));
        return card;
    }

    private void showToyDetails(Toy toy) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Інформація про іграшку");
        dialog.setHeaderText("Деталі про \"" + toy.getName() + "\"");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        DialogStyler.style(dialog.getDialogPane());

        Label content = new Label(toy.toString());
        content.setWrapText(true);
        content.setStyle("-fx-padding: 10;");

        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
    }


    private void handleAdd() {
        AddToyDialog addToyDialog = new AddToyDialog(toys, budget, conn);
        addToyDialog.execute();

        budget = addToyDialog.getBudget();
        updateBudgetLabel();

        refreshToyList();
    }

    public void updateDisplayedToys(List<Toy> toysToDisplay) {
        toyDisplayPanel.getChildren().clear();

        if (toysToDisplay.isEmpty()) {
            toyDisplayPanel.getChildren().add(new Label("Жодної іграшки не знайдено."));
        } else {
            for (Toy toy : toysToDisplay) {
                toyDisplayPanel.getChildren().add(createToyCard(toy));
            }
        }
    }

    private void updateBudgetLabel() {
        budgetLabel.setText("Бюджет: ₴" + budget);
    }

    private void handleSort() {
        SortToysDialog sortCommand = new SortToysDialog(toys);
        sortCommand.setRefreshCallback(this::refreshToyList); // <-- встановлюємо метод для оновлення панелі
        sortCommand.execute();
    }
    private void handleUpdateBudget() {
        TextInputDialog dialog = new TextInputDialog(String.valueOf(budget));
        dialog.setTitle("Оновити бюджет");
        dialog.setHeaderText("Введіть новий бюджет:");
        dialog.setContentText("Сума:");

        DialogStyler.style(dialog.getDialogPane());

        dialog.showAndWait().ifPresent(input -> {
            int newBudget = Integer.parseInt(input.trim());
            if (newBudget >= 0) {
                budget = newBudget;
                updateBudgetLabel();
            }
        });
    }


}
