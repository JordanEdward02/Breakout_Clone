module COMP2013.coursework {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens code.Menu to javafx.fxml;

    exports code.Menu;
}