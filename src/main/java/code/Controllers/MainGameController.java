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

public class MainGameController implements Initializable {
    private ElementsManager m_GameManager;
    private Stage m_Stage;
    private Scene m_Scene;
    private DebugMenuController m_debugMenu;
    private GameBoardPainter m_GameBoardPainter;
    private GameLoop m_GameTimer;
    private boolean m_Pause = true;
    @FXML
    private AnchorPane m_PauseMenuPane;
    @FXML
    private Canvas m_GameBoard;
    @FXML
    private Label m_GameInfo;

    private void loadScene(Event event, String fxmlFile)
    {
        try
        {
            SFXPlayer.ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetTheme()).toExternalForm());
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

    public void setPaused()
    {
        m_Pause = true;
        m_GameTimer.stop();
        m_GameBoardPainter.SetMessage("PAUSED");
        m_GameBoardPainter.Refresh();
    }

    private void showPauseMenu()
    {
        m_PauseMenuPane.setVisible(true);
        m_PauseMenuPane.toFront();
        m_PauseMenuPane.requestFocus();
    }

    public void GameContinue()
    {
        SFXPlayer.ButtonSFX();
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.requestFocus();
        m_GameBoard.toFront();
    }

    public void GameRestart()
    {
        SFXPlayer.ButtonSFX();
        m_GameManager.BallReset();
        m_GameManager.PaddleReset();
        m_GameManager.WallReset();
        m_GameTimer.stop();
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.requestFocus();
        m_GameBoard.toFront();
        m_GameBoardPainter.Refresh();
    }

    public void GameExit(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/StartFrame.fxml");
    }

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
        m_GameBoardPainter = new GameBoardPainter(m_GameBoard, m_GameManager, m_GameInfo);
        m_GameBoardPainter.Refresh();
        m_GameTimer = GameLoop.GetGameLoop();
        m_GameTimer.SetGameData(m_GameManager, m_GameBoardPainter, this);
    }

}
