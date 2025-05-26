package front;

import Service.DatabaseUtil;
import front.MainGUI.ToyMenuGUI;
import front.MainGUI.WelcomeWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {
        Connection conn = DatabaseUtil.getConnection();

        WelcomeWindow welcomeWindow = new WelcomeWindow();
        welcomeWindow.show(primaryStage, () -> {
            ToyMenuGUI gui = new ToyMenuGUI(new ArrayList<>(), conn, primaryStage);
            Scene scene = new Scene(gui, 1000, 600);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Менеджер іграшок JavaFX");
            primaryStage.show();
        });
    }


    public static void main(String[] args) {
        launch();
    }
}
