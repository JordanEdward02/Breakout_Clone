package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.GameBoard;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MainGameController {
    private static MainGameController m_GameController;
    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;

    public static MainGameController GetGameController()
    {
        if (m_GameController == null)
        {
            m_GameController = new MainGameController();
        }
        return m_GameController;
    }

    public void SetGameManager(ElementsManager GameManager)
    {
        m_GameManager = GameManager;
    }

    public void SetGameBoard(GameBoard GameBoard)
    {
        m_GameBoard = GameBoard;
    }

    public void MainGameInput(KeyEvent e)
    {
        Paddle tempPaddle = m_GameManager.GetPaddle();
        boolean showPauseMenu = m_GameBoard.GetShowPauseMenu();
        Timer gameTimer = m_GameBoard.GetGameTimer();
        int code=e.getKeyCode();
        switch(code)
        {
            case KeyEvent.VK_LEFT:
                tempPaddle.MoveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                tempPaddle.MoveRight();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu) {
                    if (gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                m_GameBoard.SetShowPauseMenu(!showPauseMenu);
                m_GameBoard.repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_F1:
                if(e.isAltDown() && e.isShiftDown())
                    m_GameBoard.GetDebugConsole().setVisible(true);
                break;
            default:
        }
    }

}
