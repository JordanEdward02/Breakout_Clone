module COMP2013.coursework {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;

    exports code;
    exports code.Controllers;
    exports code.GameplayElements;
    exports code.Menu;

    opens code.Controllers;
    opens code.Menu;
}