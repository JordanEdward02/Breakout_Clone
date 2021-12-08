package code.Controllers;

import code.GameplayElements.Levels.LevelManager;
import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

/**
 * Controller for the Start Menu of the game
 * @author Jordan Lovett
 */
public class StartMenuController {

    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;
    private SFXPlayer m_SFXPlayer;

    /**
     * Default constructor that initialises the sound effects player
     */
    public StartMenuController()
    {
        m_SFXPlayer = new SFXPlayer();
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

    /**
     * Loads the levels menu
     * @param event Action Event from the button press
     */
    public void loadLevels(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/LevelChoiceFrame.fxml");
    }

    /**
     * Returns to the start menu
     * @param event Action Event from the button press
     */
    public void returnToStart(ActionEvent event)
    {
        loadScene(event,"/Menu/Frames/StartFrame.fxml");
    }

    /**
     * Loads the first level
     * @param event Action Event from the level 1 button
     */
    public void startLevel1(ActionEvent event)
    {
        LevelManager.setStartLevel(1);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    /**
     * Loads the second level
     * @param event Action Event from the level 2 button
     */
    public void startLevel2(ActionEvent event)
    {
        LevelManager.setStartLevel(2);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    /**
     * Loads the third level
     * @param event Action Event from the level 3 button
     */
    public void startLevel3(ActionEvent event)
    {
        LevelManager.setStartLevel(3);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    /**
     * Loads the forth level
     * @param event Action Event from the level 4 button
     */
    public void startLevel4(ActionEvent event)
    {
        LevelManager.setStartLevel(4);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    /**
     * Loads the fifth level
     * @param event Action Event from the level 5 button
     */
    public void startLevel5(ActionEvent event)
    {
        LevelManager.setStartLevel(5);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    /**
     * Loads the sixth level
     * @param event Action Event from the level 6 button
     */
    public void startLevel6(ActionEvent event)
    {
        LevelManager.setStartLevel(6);
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }

    /**
     * Loads the high scores menu
     * @param event Action Event from the high score button
     */
    public void loadHighscoresMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/HighscoresMenu.fxml");
    }

    /**
     * Loads the theme menu
     * @param event Action Event from the theme menu button
     */
    public void LoadThemeMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/ThemeMenu.fxml");
    }
}
