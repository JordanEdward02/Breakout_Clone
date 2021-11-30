package code.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;

public class ThemeMenuController {


    public void SetTheme(ActionEvent event, String newCSS)
    {
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().removeAll();
        scene.getStylesheets().add(newCSS);
        ThemeMaintainer.SetTheme(newCSS);
    }

    public void SetThemeClassic(ActionEvent event)
    {
        SetTheme(event, "/CSS/Classic.css");
    }

    public void SetThemeSimple(ActionEvent event)
    {
        SetTheme(event, "/CSS/Simple.css");
    }

    public void SetThemeNeon(ActionEvent event)
    {
        SetTheme(event, "/CSS/Neon.css");
    }
}
