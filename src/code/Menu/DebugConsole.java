package code.Menu;

import code.GameplayElements.Ball;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame m_owner;
    private DebugPanel m_debugPanel;
    private GameBoard m_gameBoard;
    private ElementsManager m_gameManager;


    private void setLocation(){
        int x = ((m_owner.getWidth() - this.getWidth()) / 2) + m_owner.getX();
        int y = ((m_owner.getHeight() - this.getHeight()) / 2) + m_owner.getY();
        this.setLocation(x,y);
    }

    public DebugConsole(JFrame owner,ElementsManager gameManager,GameBoard gameBoard){

        m_gameManager = gameManager;
        m_owner = owner;
        m_gameBoard = gameBoard;
        initialize();

        m_debugPanel = new DebugPanel(m_gameManager);
        this.add(m_debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        m_gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = m_gameManager.GetBall();
        m_debugPanel.SetValues(b.GetSpeedX(),b.GetSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
