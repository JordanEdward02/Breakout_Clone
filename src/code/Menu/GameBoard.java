package code.Menu;

import code.Controllers.GameControllerBrain;
import code.GameplayElements.*;
import code.GameplayElements.Bricks.Brick;
import code.Menu.Debug.DebugConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;



public class GameBoard extends JComponent{

    // Far too many class viriables for this to be maintainable. When creating the pause menu class, hopefully lots of
    // these will be moved and the
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


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

    private Font m_MenuFont;

    private Rectangle m_continueButtonRect;
    private Rectangle m_exitButtonRect;
    private Rectangle m_restartButtonRect;
    private int m_MenuInt=0;

    private DebugConsole m_debugConsole;

    private GameControllerBrain m_GameController;

    public boolean GetShowPauseMenu()
    {
        return m_ShowPauseMenu;
    }

    public GameLoop GetGameLoop()
    {
        return m_GameLoop;
    }

    public Rectangle GetContinueBut()
    {
        return m_continueButtonRect;
    }

    public Rectangle GetExitBut()
    {
        return m_exitButtonRect;
    }

    public Rectangle GetRestartBut()
    {
        return m_restartButtonRect;
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
        m_MenuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        this.initialize();
        m_Message = "Press SPACE to start";

        m_GameManager = new ElementsManager(new Wall(), new Point(DEF_BALL_X,DEF_BALL_Y),new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));
        m_debugConsole = new DebugConsole(owner,m_GameManager,this);
        m_GameController = GameControllerBrain.getControllerBrain();
        m_GameController.SetGame(m_GameManager, this);
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
            drawMenu(g2d);

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

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();
        final float fAlphaFloat=0.55f;

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,fAlphaFloat);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();
        g2d.setFont(m_MenuFont);
        g2d.setColor(MENU_COLOR);
        final int iTWO=2, iTEN=10, iEIGHT=8, iFOUR=4;
        final double dTHREE=3.0;

        if(m_MenuInt == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            m_MenuInt = m_MenuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - m_MenuInt) / iTWO;
        int y = this.getHeight() / iTEN;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / iEIGHT;
        y = this.getHeight() / iFOUR;


        if(m_continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            m_continueButtonRect = m_MenuFont.getStringBounds(CONTINUE,frc).getBounds();
            m_continueButtonRect.setLocation(x,y-m_continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= iTWO;

        if(m_restartButtonRect == null){
            m_restartButtonRect = (Rectangle) m_continueButtonRect.clone();
            m_restartButtonRect.setLocation(x,y-m_restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= dTHREE/iTWO;

        if(m_exitButtonRect == null){
            m_exitButtonRect = (Rectangle) m_continueButtonRect.clone();
            m_exitButtonRect.setLocation(x,y-m_exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public void OnLostFocus(){
        m_GameLoop.TimerStop();
        m_Message = "Focus Lost";
        repaint();
    }

}
