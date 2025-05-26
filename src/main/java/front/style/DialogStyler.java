package front.style;

import javafx.scene.control.DialogPane;

public class DialogStyler {
    public static void style(DialogPane dialogPane) {
        dialogPane.getStylesheets().add(DialogStyler.class.getResource("/styles/styles.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-dialog");
    }
}
