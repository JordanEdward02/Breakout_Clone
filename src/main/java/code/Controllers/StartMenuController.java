package code.Controllers;

import code.GameplayElements.Levels.LevelManager;
import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;


public class StartMenuController {

    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;
    private SFXPlayer m_SFXPlayer;

    public StartMenuController()
    {
        m_SFXPlayer = SFXPlayer.GetSFXPlayer();
    }

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            m_SFXPlayer.ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(getClass().getResource(fxmlFile));
            m_Scene = new Scene(m_Root);
            m_Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetThemeMaintainer().GetTheme()).toExternalForm());
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadLevels(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/LevelChoiceFrame.fxml");
    }

    public void returnToStart(ActionEvent event)
    {
        loadScene(event,"/Menu/Frames/StartFrame.fxml");
    }

    public void startLevel1(ActionEvent event)
    {
        LevelManager.setStartLevel(1);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel2(ActionEvent event)
    {
        LevelManager.setStartLevel(2);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel3(ActionEvent event)
    {
        LevelManager.setStartLevel(3);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel4(ActionEvent event)
    {
        LevelManager.setStartLevel(4);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel5(ActionEvent event)
    {
        LevelManager.setStartLevel(5);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel6(ActionEvent event)
    {
        LevelManager.setStartLevel(6);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }

    public void loadHighscoresMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/HighscoresMenu.fxml");
    }

    public void LoadThemeMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/ThemeMenu.fxml");
    }
}
