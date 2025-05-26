module Lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    opens front to javafx.fxml;
    opens back.Toy to javafx.fxml;
    exports front;
    exports front.ActionGUI;
    opens front.ActionGUI to javafx.fxml;
    exports front.MainGUI;
    opens front.MainGUI to javafx.fxml;
    exports front.style;
    opens front.style to javafx.fxml;
}