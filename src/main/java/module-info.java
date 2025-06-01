module Lab4{
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;
    requires org.slf4j;
    requires jakarta.mail;
    opens front to javafx.fxml;
    opens back.Toy to javafx.fxml;
    exports front;
    exports front.ActionGUI;
    opens front.ActionGUI to javafx.fxml;
    exports front.MainGUI;
    opens front.MainGUI to javafx.fxml;
    exports front.style;
    opens front.style to javafx.fxml;
    exports front.ActionGUI.Adding;
    opens front.ActionGUI.Adding to javafx.fxml;
    exports front.ActionGUI.Deleter;
    opens front.ActionGUI.Deleter to javafx.fxml;
    exports front.ActionGUI.Finder;
    opens front.ActionGUI.Finder to javafx.fxml;
    exports front.ActionGUI.Sorter;
    opens front.ActionGUI.Sorter to javafx.fxml;
    exports front.MainGUI.MainWindow;
    opens front.MainGUI.MainWindow to javafx.fxml;
    exports back.Log;
}