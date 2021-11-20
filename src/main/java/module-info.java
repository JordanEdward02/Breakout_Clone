module COMP2013.coursework {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    exports code;

    opens code to javafx.fxml;
}