package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.*;
import code.Menu.Frames.GameBoard;
import code.Menu.Painters.GameBoardPainter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import java.util.Objects;

public class MainGameController {
    private static MainGameController m_GameController;
    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;
    private GameBoardPainter m_GameBoardPainter;
    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;
    private boolean m_Paused;
    @FXML
    public AnchorPane m_MainGamePane;

    private void loadScene(Event event, String fxmlFile)
    {
        try
        {
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static MainGameController GetGameController()
    {
        if (m_GameController == null)
        {
            m_GameController = new MainGameController();
        }
        return m_GameController;
    }


    public void KeyPress(KeyEvent event)
    {
        Paddle tempPaddle = m_GameManager.GetPaddle();
        boolean showPauseMenu = m_GameBoard.GetShowPauseMenu();
        GameLoop gameTimer = m_GameBoard.GetGameLoop();
        KeyCode code=event.getCode();
        switch(code)
        {
            case KP_LEFT:
                tempPaddle.MoveLeft();
                break;
            case KP_RIGHT:
                tempPaddle.MoveRight();
                break;
            case SPACE:
                if(!showPauseMenu) {
                    if (gameTimer.LoopIsRunning())
                        gameTimer.TimerStop();
                    else
                        gameTimer.LoopStart();
                }
                break;
            case ESCAPE:
                loadScene(event, "/Menu/Frames/PauseMenu.fxml");
                togglePause();
                gameTimer.TimerStop();
                break;
            case F1:
                if(event.isAltDown() && event.isShiftDown())
                    loadScene(event, "/Menu/Frames/DebugMenu.fxml");
                break;
            default:
        }
    }

    private void togglePause()
    {
        if (m_Paused) {
            m_Paused = false;
            m_MainGamePane.toFront();
        }
        else {
            m_Paused = true;
            m_MainGamePane.toBack();
        }
    }

    public void GameContinue(ActionEvent event)
    {
        if (m_Paused)
        {
            m_MainGamePane.toBack();
        }
    }

    public void GameRestart(ActionEvent event)
    {
        if (m_Paused)
        {
            m_GameManager.BallReset();
            m_GameManager.PaddleReset();
            m_GameManager.WallReset();
        }
    }

    public void GameExit(ActionEvent event)
    {
        if (m_Paused)
        {
            loadScene(event, "/Menu/Frames/StartFrame.fxml");
        }
    }
}
