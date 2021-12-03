module COMP2013.coursework {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;

    exports code;
    exports code.Controllers;

    opens code.Controllers;
    exports code.Menu;
    opens code.Menu;
}