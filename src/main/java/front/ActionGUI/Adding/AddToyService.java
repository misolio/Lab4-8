package front.ActionGUI.Adding;

import back.Control.ToyAdder;
import back.Loader.*;
import back.Toy.Toy;
import front.style.AlertStyler;
import front.style.DialogStyler;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddToyService {
    private final ToyDialogView view;
    private final ToyAdder toyAdder;
    protected final Map<Integer, Loading> loaders;

    public AddToyService(List<Toy> toys, int budget, Connection conn, ToyDialogView view) {
        this.view = view;
        this.toyAdder = new ToyAdder(toys, budget);
        this.loaders = Map.of(
                1, new BoardGameLoader(conn),
                2, new DollLoader(conn),
                3, new TransportLoader(conn),
                4, new PlushToyLoader(conn),
                5, new EducationalToyLoader(conn)
        );
    }
    public AddToyService(List<Toy> toys, int budget, Connection conn, ToyDialogView view, Map<Integer, Loading> loaders) {
        this.view = view;
        this.toyAdder = new ToyAdder(toys, budget);
        this.loaders = loaders;
    }

    public void run() {
        boolean continueAdding = true;
        while (continueAdding) {
            int type = view.promptToyType();
            if (type == -1) return;

            List<Toy> availableToys = loaders.get(type).loadAll();
            if (availableToys.isEmpty()) {
                view.showAlert(Alert.AlertType.INFORMATION, "Немає доступних іграшок цього типу.");
                continue;
            }

            List<Toy> selectedToys = view.promptToySelection(availableToys, toyAdder.getBudget());
            if (selectedToys != null) {
                StringBuilder report = new StringBuilder();
                for (Toy toy : selectedToys) {
                    if (toyAdder.addToyIfAffordable(toy)) {
                        report.append("✔ ").append(toy.getName()).append("\n");
                    } else {
                        report.append("✖ Недостатньо бюджету для ").append(toy.getName()).append("\n");
                    }
                }
                view.showAlert(Alert.AlertType.INFORMATION, report.toString());
            }

            continueAdding = view.confirm("Додати ще іграшки?");
        }
    }

    public int getBudget() {
        return toyAdder.getBudget();
    }

    public static class ToyDialogFXView implements ToyDialogView {

        @Override
        public int promptToyType() {
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Настільні ігри",
                    "Настільні ігри", "Ляльки", "Транспорт", "М’які іграшки", "Освітні іграшки");
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

        @Override
        public List<Toy> promptToySelection(List<Toy> availableToys, int budget) {
            ListView<Toy> listView = new ListView<>(FXCollections.observableList(availableToys));
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            listView.setPrefHeight(Math.min(availableToys.size(), 8) * 100 + 10);
            listView.setPrefWidth(800);

            listView.setCellFactory(param -> new ListCell<>() {
                private final Text text = new Text();
                private final TextFlow textFlow = new TextFlow(text);
                private final StackPane container = new StackPane();

                {
                    text.wrappingWidthProperty().bind(listView.widthProperty().subtract(40));
                    text.getStyleClass().add("toy-card-text");
                    textFlow.setTextAlignment(TextAlignment.JUSTIFY);
                    textFlow.setLineSpacing(2);
                    container.setPadding(new Insets(10));
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
            dialog.setHeaderText("Бюджет: ₴" + budget);
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

        @Override
        public boolean confirm(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
            AlertStyler.style(alert);
            return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
        }

        @Override
        public void showAlert(Alert.AlertType type, String message) {
            Alert alert = new Alert(type);
            AlertStyler.style(alert);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}

