package front.MainGUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WelcomeWindow {

    public void show(Stage primaryStage, Runnable onStart) {
        Stage welcomeStage = new Stage();
        welcomeStage.setTitle("Ласкаво просимо!");

        Label greeting = new Label("Вітаємо у кімнаті іграшок!");
        greeting.getStyleClass().add("welcome-label");

        Button startButton = new Button("Почати");
        startButton.getStyleClass().add("welcome-button");

        startButton.setOnAction(e -> {
            welcomeStage.close();
            onStart.run(); // Дія після натискання
        });

        VBox layout = new VBox(20, greeting, startButton);
        layout.setAlignment(Pos.CENTER);
        layout.getStyleClass().add("welcome-pane");

        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        welcomeStage.setScene(scene);
        welcomeStage.initOwner(primaryStage);
        welcomeStage.initModality(Modality.APPLICATION_MODAL);
        welcomeStage.show();
    }
}
