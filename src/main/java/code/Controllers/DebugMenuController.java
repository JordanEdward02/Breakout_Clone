package code.Controllers;

import code.GameplayElements.Ball;
import code.GameplayElements.ElementsManager;
import code.Menu.Frames.DebugConsole;
import code.Menu.Painters.DebugPanelPainter;
import code.Menu.Frames.GameBoard;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugMenuController implements WindowListener {

    private DebugConsole m_DebugConsole;
    private GameBoard m_GameBoard;
    private ElementsManager m_GameManager;
    private DebugPanelPainter m_DebugPanelPainter;

    public JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    public JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    public DebugMenuController(GameBoard gameBoard, DebugConsole debugConsole, ElementsManager gameManager, DebugPanelPainter debugPanelPainter)
    {
        m_GameBoard = gameBoard;
        m_DebugConsole = debugConsole;
        m_GameManager = gameManager;
        m_DebugPanelPainter = debugPanelPainter;
    }

    public void LevelSkipButton()
    {
        if (m_GameManager.NewLevel())
        {
            m_GameManager.LevelSkip();
            m_GameBoard.repaint();
        }
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
