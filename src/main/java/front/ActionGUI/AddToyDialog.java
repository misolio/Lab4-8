package front.ActionGUI;

import back.Control.ToyAdder;
import back.Toy.Toy;
import back.Loader.*;

import front.style.AlertStyler;
import front.style.DialogStyler;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.*;


import java.sql.Connection;
import java.util.*;

public class AddToyDialog implements Command {
    private final List<Toy> toys;
    final Map<Integer, Loading> loaders = new HashMap<>();
    private final ToyAdder toyAdder;

    public AddToyDialog(List<Toy> toys, int budget, Connection conn) {
        this.toys = toys;
        this.toyAdder = new ToyAdder(toys, budget);

        loaders.put(1, new BoardGameLoader(conn));
        loaders.put(2, new DollLoader(conn));
        loaders.put(3, new TransportLoader(conn));
        loaders.put(4, new PlushToyLoader(conn));
        loaders.put(5, new EducationalToyLoader(conn));
    }

    @Override
    public void execute() {
        boolean continueAdding = true;

        while (continueAdding) {
            int type = promptToyType();
            if (type == -1) return;

            List<Toy> availableToys = loaders.get(type).loadAll();
            if (availableToys.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Немає доступних іграшок цього типу.");
                continue;
            }

            List<Toy> selectedToys = promptToySelection(availableToys);
            if (selectedToys != null) {
                StringBuilder report = new StringBuilder();
                for (Toy toy : selectedToys) {
                    if (toyAdder.addToyIfAffordable(toy)) {
                        report.append("✔ ").append(toy.getName()).append("\n");
                    } else {
                        report.append("✖ Недостатньо бюджету для ").append(toy.getName()).append("\n");
                    }
                }
                showAlert(Alert.AlertType.INFORMATION, report.toString());
            }

            continueAdding = confirm("Додати ще іграшки?");
        }
    }

    protected int promptToyType() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Настільні ігри", "Настільні ігри", "Ляльки", "Транспорт", "М’які іграшки", "Освітні іграшки");
        DialogStyler.style(dialog.getDialogPane());
        dialog.setTitle("Тип іграшки");
        dialog.setHeaderText("Оберіть тип іграшок для перегляду:");
        dialog.setContentText("Тип:");

        Optional<String> result = dialog.showAndWait();
        if (result.isEmpty()) return -1;

        return switch (result.get()) {
            case "Настільні ігри" -> 1;
            case "Ляльки" -> 2;
            case "Транспорт" -> 3;
            case "М’які іграшки" -> 4;
            case "Освітні іграшки" -> 5;
            default -> -1;
        };
    }

    protected List<Toy> promptToySelection(List<Toy> availableToys) {
        ListView<Toy> listView = new ListView<>(FXCollections.observableList(availableToys));
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setPrefHeight(Math.min(availableToys.size(), 8) * 100 + 10); // динамічна висота
        listView.setPrefWidth(800);
        // Заміна фабрики осередків на картковий вигляд
        listView.setCellFactory(param -> new ListCell<>() {
            private final Text text = new Text();
            private final TextFlow textFlow = new TextFlow(text);
            private final StackPane container = new StackPane();

            {
                    text.wrappingWidthProperty().bind(listView.widthProperty().subtract(40)); // адаптуємо ширину тексту
                    text.getStyleClass().add("toy-card-text");

                    textFlow.setTextAlignment(TextAlignment.JUSTIFY);
                    textFlow.setLineSpacing(2);

                container.setPadding(new Insets(10)); // Відступи з усіх сторін
                container.getChildren().add(textFlow);

                container.getStyleClass().add("toy-card");
            }

            @Override
            protected void updateItem(Toy toy, boolean empty) {
                    super.updateItem(toy, empty);
                    if (empty || toy == null) {
                        setGraphic(null);
                    } else {
                        text.setText(toy.toString());
                        setGraphic(container);
                    }
                }

        });

        Dialog<List<Toy>> dialog = new Dialog<>();
        dialog.setTitle("Вибір іграшок");
        dialog.setHeaderText("Бюджет: ₴" + toyAdder.getBudget());
        dialog.getDialogPane().getStyleClass().add("toy-selection-dialog");
        dialog.getDialogPane().setPrefWidth(600);

        DialogStyler.style(dialog.getDialogPane());
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(listView);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                return new ArrayList<>(listView.getSelectionModel().getSelectedItems());
            }
            return null;
        });

        Optional<List<Toy>> result = dialog.showAndWait();
        return result.orElse(null);
    }


    protected boolean confirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        AlertStyler.style(alert);
        return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }

    protected void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        AlertStyler.style(alert);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public int getBudget() {
        return toyAdder.getBudget();
    }
}
