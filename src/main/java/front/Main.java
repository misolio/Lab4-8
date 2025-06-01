package front;

import Service.DatabaseUtil;
import back.Log.LoggerUtility;
import front.MainGUI.MainWindow.ToyMenuGUI;
import front.MainGUI.WelcomeWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    @Override
    public void start(Stage primaryStage) {
        try {
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

        } catch (SQLException e) {
            LoggerUtility.logError(" Не вдалося підключитися до бази даних!", e);
            // Можна також показати alert користувачу:
            javafx.application.Platform.runLater(() -> {
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "Помилка підключення до БД:\n" + e.getMessage()).showAndWait();
            });
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
