package code.Menu.Painters;

import code.GameplayElements.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.Frames.GameBoard;

import java.awt.*;

public class GameBoardPainter {

    private static final int TWOFIFTY = 250;
    private static final int TWOTWENTYFIVE = 225;

    private GameBoard m_GameBoard;
    private PauseMenuPainter m_PauseMenuPainter;
    private ElementsManager m_GameManager;
    private String m_Message;

    public void SetPauseMenuPainter(PauseMenuPainter newPausePainter)
    {
        m_PauseMenuPainter = newPausePainter;
    }

    public void SetGameManager(ElementsManager newGameManager)
    {
        m_GameManager = newGameManager;
    }

    public void SetMessage(String newMessage)
    {
        m_Message = newMessage;
    }

    public GameBoardPainter(GameBoard GameBoard)
    {
        m_GameBoard = GameBoard;
    }

    public void MainPaint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        m_GameBoard.clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(m_Message,TWOFIFTY,TWOTWENTYFIVE);

        drawBall(m_GameManager.GetBall(),g2d);
        for(Brick b : m_GameManager.GetWall().GetBricks())
            if(!b.IsBroken())
                drawBrick(b,g2d);

        drawPlayer(m_GameManager.GetPaddle(),g2d);

        if(m_GameBoard.GetShowPauseMenu())
            m_PauseMenuPainter.DrawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();

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

}
