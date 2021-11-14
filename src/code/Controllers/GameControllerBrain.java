package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.GameBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GameControllerBrain implements KeyListener, MouseListener, MouseMotionListener {

    private static GameControllerBrain m_ControllerBrain;
    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;
    // Please note that these extra controllers look pointless, but allows the option for users to just swap out
    // another controller to change things like gameplay. I may integrate other options for this when I make additions
    private PauseMenuController m_PauseMenuController;
    private MainGameController m_GameController;


    public static GameControllerBrain getControllerBrain()
    {
        if (m_ControllerBrain == null)
        {
            m_ControllerBrain = new GameControllerBrain();
        }
        return m_ControllerBrain;
    }

    public void SetGame(ElementsManager GameManager, GameBoard GameBoard)
    {
        m_GameManager = GameManager;
        m_GameBoard = GameBoard;
        initialise();
    }

    private void initialise()
    {
        m_GameBoard.addKeyListener(this);
        m_GameBoard.addMouseListener(this);
        m_GameBoard.addMouseMotionListener(this);
        m_PauseMenuController = PauseMenuController.GetPauseMenuController();
        m_PauseMenuController.SetGameManager(m_GameManager);
        m_PauseMenuController.SetGameBoard(m_GameBoard);
        m_GameController = MainGameController.GetGameController();
        m_GameController.SetGameManager(m_GameManager);
        m_GameController.SetGameBoard(m_GameBoard);

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        m_GameController.MainGameInput(e);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        m_GameManager.StopPaddle();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (m_GameBoard.GetShowPauseMenu()) {
            m_PauseMenuController.PauseMenuInputs(mouseEvent);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (m_GameBoard.GetShowPauseMenu()) {
            m_PauseMenuController.PauseMenuVisuals(mouseEvent);
        }
        else{
            m_GameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
