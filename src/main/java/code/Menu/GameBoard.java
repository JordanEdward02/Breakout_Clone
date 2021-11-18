package code.Menu;

import code.Controllers.GameControllerBrain;
import code.GameplayElements.*;
import code.GameplayElements.Bricks.Brick;
import code.Menu.Debug.DebugConsole;
import code.Menu.Painters.GameBoardPainter;
import code.Menu.Painters.PauseMenuPainter;

import javax.swing.*;
import java.awt.*;


public class GameBoard extends JComponent{

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final int DEF_BALL_X = 300;
    private static final int DEF_BALL_Y = 420;

    private static final Color BG_COLOR = Color.WHITE;

    private GameLoop m_GameLoop;

    private ElementsManager m_GameManager;

    private boolean m_ShowPauseMenu;

    private DebugConsole m_debugConsole;
    private GameControllerBrain m_GameController;
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

    public DebugConsole GetDebugConsole()
    {
        return m_debugConsole;
    }

    public GameBoard(JFrame owner){
        super();
        m_ShowPauseMenu = false;
        this.initialize();
        m_PausePainter = new PauseMenuPainter();
        m_GameManager = new ElementsManager(new Wall(), new Point(DEF_BALL_X,DEF_BALL_Y),new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));
        m_BoardPainter = new GameBoardPainter(this);
        m_BoardPainter.SetMessage("Press SPACE to start");
        m_BoardPainter.SetPauseMenuPainter(m_PausePainter);
        m_BoardPainter.SetGameManager(m_GameManager);
        m_debugConsole = new DebugConsole(owner,m_GameManager,this);
        m_GameController = GameControllerBrain.getControllerBrain();
        m_GameController.SetGame(m_GameManager, this, m_PausePainter, m_BoardPainter);
        //initialize the first level
        m_GameManager.NextLevel();

        m_GameLoop = GameLoop.GetGameLoop();
        m_GameLoop.SetGameData(m_GameManager, this, m_BoardPainter);
        m_GameLoop.StartLoop();

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g){

        m_BoardPainter.MainPaint(g);
    }

    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    public void OnLostFocus(){
        m_GameLoop.TimerStop();
        m_BoardPainter.SetMessage( "Focus Lost");
        repaint();
    }

}
