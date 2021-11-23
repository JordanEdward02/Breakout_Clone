package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.GameplayElements.Wall;
import code.Menu.Painters.GameBoardPainter;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

// Look into making static function to return the controller. If this is a singleton then you will be able to call functions
// on it to start from select levels


public class MainGameController implements Initializable {
    private ElementsManager m_GameManager;
    private Stage m_Stage;
    private Scene m_Scene;
    private DebugMenuController m_debugMenu;
    private GameBoardPainter m_GameBoardPainter;
    @FXML
    public AnchorPane m_PauseMenuPane;
    @FXML
    private Canvas m_GameBoard;

    private void loadScene(Event event, String fxmlFile)
    {
        try
        {
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
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
                //gameTimer.TimerStop();
                break;
            case ESCAPE:
                showPauseMenu();
                //gameTimer.TimerStop();
                break;
            case F1:
                if(event.isAltDown() && event.isShiftDown()) {
                    Stage newStage = new Stage();
                    m_debugMenu = new DebugMenuController(newStage);
                }
                break;
            default:
        }
        m_GameManager.Move();
        m_GameBoardPainter.Refresh();
    }

    private void KeyRelease()
    {
        m_GameManager.GetPaddle().Stop();
        m_GameManager.Move();
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
        m_PauseMenuPane.setVisible(false);
        m_GameBoard.requestFocus();
        m_GameBoard.toFront();
    }

    public void GameRestart()
    {
        /*
        m_GameManager.BallReset();
        m_GameManager.PaddleReset();
        m_GameManager.WallReset();

         */
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
        m_GameBoardPainter = new GameBoardPainter(m_GameBoard, m_GameManager);
        m_GameBoardPainter.Refresh();
    }

}
