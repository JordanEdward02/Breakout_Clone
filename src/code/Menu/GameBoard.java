package code.Menu;

import code.Controllers.GameControllerBrain;
import code.GameplayElements.*;
import code.GameplayElements.Bricks.Brick;
import code.Menu.Debug.DebugConsole;

import javax.swing.*;
import java.awt.*;


public class GameBoard extends JComponent{

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    // Added so when creating ball we adhere to Bob's conventions
    private static final int DEF_BALL_X = 300;
    private static final int DEF_BALL_Y = 420;

    private static final Color BG_COLOR = Color.WHITE;

    private GameLoop m_GameLoop;

    private ElementsManager m_GameManager;

    private String m_Message;

    private boolean m_ShowPauseMenu;

    private DebugConsole m_debugConsole;
    private GameControllerBrain m_GameController;
    private PauseMenu m_PauseFrame;

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

    public void SetMessage(String newMessage)
    {
        m_Message = newMessage;
    }

    public DebugConsole GetDebugConsole()
    {
        return m_debugConsole;
    }

    public GameBoard(JFrame owner){
        super();
        m_ShowPauseMenu = false;
        this.initialize();
        m_Message = "Press SPACE to start";
        m_PauseFrame = new PauseMenu();

        m_GameManager = new ElementsManager(new Wall(), new Point(DEF_BALL_X,DEF_BALL_Y),new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));
        m_debugConsole = new DebugConsole(owner,m_GameManager,this);
        m_GameController = GameControllerBrain.getControllerBrain();
        m_GameController.SetGame(m_GameManager, this, m_PauseFrame);
        //initialize the first level
        m_GameManager.NextLevel();

        m_GameLoop = GameLoop.GetGameLoop();
        m_GameLoop.SetGameData(m_GameManager, this);
        m_GameLoop.StartLoop();

    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(m_Message,250,225);

        drawBall(m_GameManager.GetBall(),g2d);

        for(Brick b : m_GameManager.GetWall().GetBricks())
            if(!b.IsBroken())
                drawBrick(b,g2d);

        drawPlayer(m_GameManager.GetPaddle(),g2d);

        if(m_ShowPauseMenu)
            m_PauseFrame.DrawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.GetInnerColor());
        g2d.fill(brick.GetBrick());

        g2d.setColor(brick.GetBorderColor());
        g2d.draw(brick.GetBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.GetBallFace();

        g2d.setColor(ball.GetInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.GetBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Paddle p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.GetPaddleFace();
        g2d.setColor(Paddle.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Paddle.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    public void OnLostFocus(){
        m_GameLoop.TimerStop();
        m_Message = "Focus Lost";
        repaint();
    }

}
