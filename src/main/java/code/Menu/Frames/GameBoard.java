package code.Menu.Frames;

import code.GameplayElements.*;
import code.Menu.GameLoop;
import code.Menu.Painters.GameBoardPainter;
import code.Menu.Painters.PauseMenuPainter;
import javafx.scene.Node;

import java.awt.*;


public class GameBoard extends Node {

    private GameLoop m_GameLoop;

    private ElementsManager m_GameManager;

    private boolean m_ShowPauseMenu;

    private PauseMenuPainter m_PausePainter;
    private GameBoardPainter m_BoardPainter;

    public boolean GetShowPauseMenu()
    {
        return m_ShowPauseMenu;
    }

    public GameLoop GetGameLoop()
    {
        return m_GameLoop;
    }

    public void SetShowPauseMenu(boolean newMenuState)
    {
        m_ShowPauseMenu = newMenuState;
    }

    public GameBoard() {
        super();
    }
        /*
        m_ShowPauseMenu = false;
        m_PausePainter = new PauseMenuPainter();
        m_GameManager = new ElementsManager(new Wall(), new Point(DEF_BALL_X,DEF_BALL_Y),new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));
        m_BoardPainter = new GameBoardPainter(this);
        m_BoardPainter.SetMessage("Press SPACE to start");
        m_BoardPainter.SetPauseMenuPainter(m_PausePainter);
        m_BoardPainter.SetGameManager(m_GameManager);
        //initialize the first level
        m_GameManager.NextLevel();

        m_GameLoop = GameLoop.GetGameLoop();
        m_GameLoop.SetGameData(m_GameManager, this, m_BoardPainter);
        m_GameLoop.StartLoop();


    }

    public void startLevel(int startLevel)
    {
        int i;
        for (i=1; i<startLevel;i++)
            m_GameManager.NextLevel();
    }

    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,(int) getScene().getWidth(),(int) getScene().getHeight());
        g2d.setColor(tmp);
    }

    public void OnLostFocus(){
        m_GameLoop.TimerStop();
        m_BoardPainter.SetMessage( "Focus Lost");
    }
*/
}
