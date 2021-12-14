package code.Controllers;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.ElementsManager;
import code.Menu.Painters.GameBoardPainter;
import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller that manages the IO with the debugMenu screen
 * @author Jordan Lovett
 */
public class DebugMenuController implements Initializable {

    private static ElementsManager m_GameManager;
    private static GameBoardPainter m_GameBoardPainter;
    private SFXPlayer m_SFXPlayer;
    @FXML
    private Slider m_XSlider;
    @FXML
    private Slider m_YSlider;
    /**
     * Sets the X speed of the ball to slider position
     */
    public void SetXSpeed()
    {
        m_GameManager.SetBallXSpeed((int)m_XSlider.getValue());
    }

    /**
     * Sets the Y speed of the ball to slider position
     */
    public void SetYSpeed()
    {
        m_GameManager.SetBallYSpeed((int)m_YSlider.getValue());
    }

    /**
     * Default constructor to create a new controller. This initialises the sound effects player
     */
    public DebugMenuController()
    {
        m_SFXPlayer = new SFXPlayer();
    }

    /**
     * Constructor used to set the controller variables
     * @param stage A stage to draw the scene and contents on
     * @param gameManager An interface for all game elements to manipulate for debugging
     * @param gameBoardPainter A view class of the other stage that needs refreshing upon changes being made
     */
    public DebugMenuController(Stage stage, ElementsManager gameManager, GameBoardPainter gameBoardPainter)
    {
        m_GameManager = gameManager;
        m_GameBoardPainter = gameBoardPainter;
        try
        {
            String fxmlFile = "/Controllers/DebugMenu.fxml";
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene Scene = new Scene(m_Root);
            Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetThemeMaintainer().GetTheme()).toExternalForm());
            stage.setScene(Scene);
            stage.setResizable(false);
            stage.setTitle("Debug Menu");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Skips the level if there's a next one, and then refreshes the GUI
     */
    public void LevelSkipButton()
    {
        m_SFXPlayer.ButtonSFX();
        if (m_GameManager.NewLevel())
        {
            m_GameManager.LevelSkip();
        }
        m_GameBoardPainter.Refresh();
    }

    /**
     * Resets the number of balls back to 3 for the user
     */
    public void ResetBalls()
    {
        m_SFXPlayer.ButtonSFX();
        m_GameManager.ResetBallCount();
        m_GameBoardPainter.Refresh();
    }
    /**
     * Sets values for the sliders before the debug GUI is drawn
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Ball b = m_GameManager.GetBall();
        m_XSlider.adjustValue(b.GetSpeedX());
        m_YSlider.adjustValue(b.GetSpeedY());
    }
}
