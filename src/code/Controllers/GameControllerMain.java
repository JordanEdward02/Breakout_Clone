package code.Controllers;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.GameBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GameControllerMain implements KeyListener, MouseListener, MouseMotionListener {

    private ElementsManager m_GameManager;
    private GameBoard m_GameBoard;
    private PauseMenuController m_PauseMenu;

    public GameControllerMain (ElementsManager GameManager, GameBoard GameBoard)
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
        m_PauseMenu = new PauseMenuController(m_GameManager, m_GameBoard);
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Paddle tempPaddle = m_GameManager.GetPaddle();
        boolean showPauseMenu = m_GameBoard.GetShowPauseMenu();
        Timer gameTimer = m_GameBoard.GetGameTimer();
        int code=e.getKeyCode();
        if(code==KeyEvent.VK_LEFT){
            tempPaddle.MoveLeft();
        }
        if(code==KeyEvent.VK_RIGHT){
            tempPaddle.MoveRight();
        }
        if(code==KeyEvent.VK_SPACE){
            if(!showPauseMenu)
                if(gameTimer.isRunning())
                    gameTimer.stop();
                else
                    gameTimer.start();
        }
        if(code==KeyEvent.VK_ESCAPE){
            m_GameBoard.SetShowPauseMenu(!showPauseMenu);
            m_GameBoard.repaint();
            gameTimer.stop();
        }
        if(code==KeyEvent.VK_F1){
            if(e.isAltDown() && e.isShiftDown())
                m_GameBoard.GetDebugConsole().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        m_GameManager.GetPaddle().Stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (m_GameBoard.GetShowPauseMenu()) {
            m_PauseMenu.PauseInputs(mouseEvent);
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
            m_PauseMenu.PauseMenuVisuals(mouseEvent);
        }
        else{
            m_GameBoard.setCursor(Cursor.getDefaultCursor());
        }
    }
}
