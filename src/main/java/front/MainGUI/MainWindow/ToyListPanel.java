package front.MainGUI.MainWindow;

import back.Toy.Toy;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class ToyListPanel {
    private final VBox panel;
    private final Label emptyLabel = new Label("Кімната порожня");
    private final ToyCardFactory cardFactory;

    public ToyListPanel(List<Toy> toys, ToyCardFactory cardFactory) {
        this.panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);
        this.cardFactory = cardFactory;

        emptyLabel.setStyle("-fx-text-fill: #888; -fx-font-style: italic;");
        panel.getChildren().add(emptyLabel);
    }

    public void refresh(List<Toy> toys) {
        panel.getChildren().clear();
        if (toys.isEmpty()) {
            panel.getChildren().add(emptyLabel);
        } else {
            for (Toy toy : toys) {
                panel.getChildren().add(cardFactory.createCard(toy));
            }
        }
    }

    public VBox getPanel() {
        return panel;
    }
}
