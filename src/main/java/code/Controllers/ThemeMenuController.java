package code.Controllers;

import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ThemeMenuController implements  Initializable{

    private Stage m_Stage;
    private Parent m_Root;
    private Scene m_Scene;
    @FXML
    public RadioButton m_ClassicButton, m_SmoothButton, m_NeonButton;

    public void SetTheme(ActionEvent event, String newCSS) {
        SFXPlayer.ButtonSFX();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(newCSS).toExternalForm());
        ThemeMaintainer.SetTheme(newCSS);
    }

    public void SetThemeClassic(ActionEvent event) {
        SetTheme(event, "/CSS/Classic.css");
        ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/ClassicTextures.png").toURI().toString()));
    }

    public void SetThemeSimple(ActionEvent event) {
        SetTheme(event, "/CSS/Smooth.css");
        ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/SmoothTextures.png").toURI().toString()));
    }

    public void SetThemeNeon(ActionEvent event) {
        SetTheme(event, "/CSS/Neon.css");
        ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/NeonTextures.png").toURI().toString()));
    }

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            SFXPlayer.ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetTheme()).toExternalForm());
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void ReturnToStart(ActionEvent event)
    {
        loadScene(event,"/Menu/Frames/StartFrame.fxml");
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
