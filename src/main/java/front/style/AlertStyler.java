package front.style;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class AlertStyler {
    public static void style(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertStyler.class.getResource("/styles/styles.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog");
    }
}
