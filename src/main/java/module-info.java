/**
 * Code for the breakout_clone. This has all the code for every aspect of the code
 * @author Jordan Lovett - Modified
 */

module COMP2013.coursework {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;

    exports code.Controllers;
    exports code.GameplayElements;
    exports code.Menu;

    opens code.Controllers;
    opens code.Menu;
    opens code.GameplayElements;
}