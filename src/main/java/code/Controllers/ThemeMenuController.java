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
    private SFXPlayer m_SFXPlayer;
    private ThemeMaintainer m_ThemeMaintainer;
    @FXML
    public RadioButton m_ClassicButton, m_SmoothButton, m_NeonButton;

    public void SetTheme(ActionEvent event, String newCSS) {
        m_SFXPlayer.ButtonSFX();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(newCSS).toExternalForm());
        m_ThemeMaintainer.SetTheme(newCSS);
    }

    public void SetThemeClassic(ActionEvent event) {
        SetTheme(event, "/CSS/Classic.css");
        m_ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/ClassicTextures.png").toURI().toString()));
    }

    public void SetThemeSimple(ActionEvent event) {
        SetTheme(event, "/CSS/Smooth.css");
        m_ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/SmoothTextures.png").toURI().toString()));
    }

    public void SetThemeNeon(ActionEvent event) {
        SetTheme(event, "/CSS/Neon.css");
        m_ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/NeonTextures.png").toURI().toString()));
    }

    public ThemeMenuController()
    {
        m_SFXPlayer = SFXPlayer.GetSFXPlayer();
        m_ThemeMaintainer = ThemeMaintainer.GetThemeMaintainer();
    }

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            m_SFXPlayer.ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Scene.getStylesheets().add(getClass().getResource(m_ThemeMaintainer.GetTheme()).toExternalForm());
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
        if(m_ThemeMaintainer.GetTheme().contains("Classic")){
            m_ClassicButton.setSelected(true);
        }
        else if (m_ThemeMaintainer.GetTheme().contains("Smooth")){
            m_SmoothButton.setSelected(true);
        }
        else{
            m_NeonButton.setSelected(true);
        }
    }
}
