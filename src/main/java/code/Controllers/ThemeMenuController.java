package code.Controllers;

import code.Menu.ThemeMaintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemeMenuController implements  Initializable{

    @FXML
    public RadioButton m_ClassicButton, m_SmoothButton, m_NeonButton;
    public void SetTheme(ActionEvent event, String newCSS) {
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(newCSS).toExternalForm());
        ThemeMaintainer.SetTheme(newCSS);
    }

    public void SetThemeClassic(ActionEvent event) {
        SetTheme(event, "/CSS/Classic.css");
    }

    public void SetThemeSimple(ActionEvent event) {
        SetTheme(event, "/CSS/Simple.css");
    }

    public void SetThemeNeon(ActionEvent event) {
        SetTheme(event, "/CSS/Neon.css");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(ThemeMaintainer.GetTheme().contains("Classic")){
            m_ClassicButton.setSelected(true);
        }
        else if (ThemeMaintainer.GetTheme().contains("Smooth")){
            m_SmoothButton.setSelected(true);
        }
        else{
            m_NeonButton.setSelected(true);
        }
    }
}
