package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.GameplayElements.Wall;
import code.Menu.GameLoop;
import code.Menu.Painters.GameBoardPainter;
import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the gameplay. This maintains all the IO operations for the actual game
 * @author Jordan Lovett
 */
public class MainGameController implements Initializable {
    private ElementsManager m_GameManager;
    private Stage m_Stage;
    private Scene m_Scene;
    private DebugMenuController m_debugMenu;
    private GameBoardPainter m_GameBoardPainter;
    private GameLoop m_GameTimer;
    private boolean m_Pause = true;
    private SFXPlayer m_SFXPlayer;
    @FXML
    private AnchorPane m_PauseMenuPane;
    @FXML
    private Canvas m_GameBoard;
    @FXML
    private Label m_GameInfo;
    @FXML
    private Label m_ScoreLabel;

    /**
     * Sets the state of the game to paused and stops the timer
     */
    public void setPaused()
    {
        m_Pause = true;
        m_GameTimer.stop();
        m_GameBoardPainter.SetMessage("PAUSED");
        m_GameBoardPainter.Refresh();
    }

    /**
     * Default constructor for the controller. This initialises the sound effects player
     */
    public MainGameController()
    {
        m_SFXPlayer = new SFXPlayer();
    }
    private void loadScene(Event event, String fxmlFile)
    {
        try
        {
            m_SFXPlayer.ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
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

    private void loadScene(String fxmlFile)
    {
        try
        {
            m_SFXPlayer.ButtonSFX();
            //Stage is null for all bricks destroyed ending. This seems odd so i'll try returning this kind of thing
            m_Stage = (Stage) m_GameBoard.getScene().getWindow();
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
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

    private void KeyPress(KeyEvent event)
    {
        Paddle tempPaddle = m_GameManager.GetPaddle();
        KeyCode code=event.getCode();
        switch(code)
        {
            case LEFT:
                tempPaddle.MoveLeft();
                break;
            case RIGHT:
                tempPaddle.MoveRight();
                break;
            case SPACE:
                togglePause();
                break;
            case ESCAPE:
                setPaused();
                showPauseMenu();
                break;
            case F1:
                if(event.isAltDown() && event.isShiftDown()) {
                    setPaused();
                    Stage newStage = new Stage();
                    m_debugMenu = new DebugMenuController(newStage, m_GameManager, m_GameBoardPainter);
                }
                break;
            default:
        }
    }

    private void KeyRelease()
    {
        m_GameManager.GetPaddle().Stop();
    }

    private void togglePause()
    {
        if (m_Pause) {
            m_Pause = false;
            m_GameTimer.start();
        }
        else {
            m_Pause = true;
            m_GameTimer.stop();
            m_GameBoardPainter.SetMessage("PAUSED");
            m_GameBoardPainter.Refresh();
        }
    }

    private void showPauseMenu()
    {
        m_PauseMenuPane.setVisible(true);
        m_PauseMenuPane.toFront();
        m_PauseMenuPane.requestFocus();
    }

    /**
     * Returns the user to the game scene and hides the pause menu
     */
    public void GameContinue()
    {
        m_SFXPlayer.ButtonSFX();
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.requestFocus();
        m_GameBoard.toFront();
    }

    /**
     * Restarts the game by refreshing the wall, ball and paddle. Also returns to the game and hides the pause menu
     */
    public void GameRestart()
    {
        m_SFXPlayer.ButtonSFX();
        m_GameManager.BallReset();
        m_GameManager.PaddleReset();
        m_GameManager.WallReset();
        m_GameTimer.stop();
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.requestFocus();
        m_GameBoard.toFront();
        m_GameBoardPainter.Refresh();
    }

    /**
     * Exits from the game and returns to the start menu
     * @param event Action Event caused by the button press
     */
    public void GameExit(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/StartFrame.fxml");
    }

    /**
     * Exits from the game without a button press, instead using the game finish menu
     */
    public void GameExit()
    {
        loadScene("/Menu/Frames/StartFrame.fxml");
    }

    /**
     * Called before the mainGame is drawn to format it and set all the data correctly
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.toFront();
        m_GameBoard.setFocusTraversable(true);
        m_GameBoard.requestFocus();
        m_GameBoard.setOnKeyPressed(event->KeyPress(event));
        m_GameBoard.setOnKeyReleased(event->KeyRelease());
        m_GameManager = new ElementsManager(new Wall(), m_GameBoard);
        m_GameManager.RenderLevel();
        m_GameBoardPainter = new GameBoardPainter(m_GameBoard, m_GameManager, m_GameInfo, m_ScoreLabel);
        m_GameBoardPainter.Refresh();
        m_GameTimer = GameLoop.GetGameLoop();
        m_GameTimer.SetGameData(m_GameManager, m_GameBoardPainter, this);
    }

}
