package code.Controllers;

import code.GameplayElements.ElementsManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import code.Menu.GameBoard;
import code.Menu.PauseMenu;

public class PauseMenuController {
    private static PauseMenuController m_ControllerPause;
    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;
    private PauseMenu m_PauseMenuFrame;

    public static PauseMenuController GetPauseMenuController()
    {
        if (m_ControllerPause == null)
        {
            m_ControllerPause = new PauseMenuController();
        }
        return m_ControllerPause;
    }

    public void SetGameManager(ElementsManager GameManager)
    {
        m_GameManager = GameManager;
    }

    public void SetGameBoard(GameBoard GameBoard)
    {
        m_GameBoard = GameBoard;
    }

    public void SetPauseMenuFrame(PauseMenu PauseFrame)
    {
        m_PauseMenuFrame = PauseFrame;
    }


    public void PauseMenuInputs(MouseEvent NewEvent)
    {
        Point p = NewEvent.getPoint();
        Rectangle exitButton = m_PauseMenuFrame.GetExitBut();
        Rectangle continueButton = m_PauseMenuFrame.GetContinueBut();
        Rectangle restartButton = m_PauseMenuFrame.GetRestartBut();
        boolean showPauseMenu = m_GameBoard.GetShowPauseMenu();
        if(continueButton.contains(p)){
            m_GameBoard.SetShowPauseMenu(false);
            m_GameBoard.repaint();
        }
        else if(restartButton.contains(p)){
            m_GameBoard.SetMessage("Restarting Game...");
            m_GameManager.BallReset();
            m_GameManager.WallReset();
            m_GameBoard.SetShowPauseMenu(false);
            m_GameBoard.repaint();
        }
        else if(exitButton.contains(p)){
            System.exit(0);
        }
    }

    public void PauseMenuVisuals(MouseEvent NewEvent)
    {
        Point p = NewEvent.getPoint();
        Rectangle exitButton = m_PauseMenuFrame.GetExitBut();
        Rectangle continueButton = m_PauseMenuFrame.GetContinueBut();
        Rectangle restartButton = m_PauseMenuFrame.GetRestartBut();

        if(exitButton != null && m_GameBoard.GetShowPauseMenu()) {
            if (exitButton.contains(p) || continueButton.contains(p) || restartButton.contains(p))
                m_GameBoard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                m_GameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
