package code.Controllers;

import code.GameplayElements.Ball;
import code.GameplayElements.ElementsManager;
import code.Menu.Frames.DebugConsole;
import code.Menu.Painters.DebugPanelPainter;
import code.Menu.Frames.GameBoard;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugMenuController implements WindowListener {

    private DebugConsole m_DebugConsole;
    private GameBoard m_GameBoard;
    private ElementsManager m_GameManager;
    private DebugPanelPainter m_DebugPanelPainter;

    public DebugMenuController(GameBoard gameBoard, DebugConsole debugConsole, ElementsManager gameManager, DebugPanelPainter debugPanelPainter)
    {
        m_GameBoard = gameBoard;
        m_DebugConsole = debugConsole;
        m_GameManager = gameManager;
        m_DebugPanelPainter = debugPanelPainter;
    }
    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {

    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        m_GameBoard.repaint();
    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {
        m_DebugConsole.setLocation();
        Ball b = m_GameManager.GetBall();
        m_DebugPanelPainter.SetValues(b.GetSpeedX(),b.GetSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
