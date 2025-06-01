package front.MainGUI.MainWindow;

import back.Control.ToyDeleter;
import back.Toy.Toy;
import front.ActionGUI.Deleter.DeleteToyDialog;
import front.style.DialogStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.List;

public class ToyCardFactory {
    private final List<Toy> toys;
    private final Runnable onToyDeleted;

    public ToyCardFactory(List<Toy> toys, Runnable onToyDeleted) {
        this.toys = toys;
        this.onToyDeleted = onToyDeleted;
    }

    public HBox createCard(Toy toy) {
        Label nameLabel = new Label("Назва: " + toy.getName());
        Label priceLabel = new Label("Ціна: ₴" + toy.getPrice());

        Button detailsBtn = new Button("Детальніше");
        detailsBtn.setOnAction(e -> showToyDetails(toy));

        Button deleteBtn = new Button("Видалити");
        deleteBtn.setOnAction(e -> {
            ToyDeleter deleter = new ToyDeleter(toys);
            new DeleteToyDialog(deleter, toy).execute();
            onToyDeleted.run();
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
        dialog.getDialogPane().setContent(new Label(toy.toString()));
        dialog.showAndWait();
    }
}
