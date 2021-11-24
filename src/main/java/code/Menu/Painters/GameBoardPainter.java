package code.Menu.Painters;

import code.GameplayElements.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.GameplayElements.Wall;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;

import java.awt.*;


public class GameBoardPainter {

    private static final int TWOFIFTY = 250;
    private static final int TWOTWENTYFIVE = 225;

    private Canvas m_GameBoard;
    private PauseMenuPainter m_PauseMenuPainter;
    private ElementsManager m_GameManager;
    private String m_Message;

    public void SetMessage(String newMessage)
    {
        m_Message = newMessage;
    }

    public GameBoardPainter(Canvas GameBoard, ElementsManager gameManager)
    {
        m_GameBoard = GameBoard;
        m_GameManager = gameManager;
        m_Message = "Press SPACE to start";
    }

    public void Refresh() {
        GraphicsContext graphics = m_GameBoard.getGraphicsContext2D();
        graphics.clearRect(0, 0, 600, 400);

        graphics.setFill(Color.BLACK);
        graphics.setStroke(Color.BLACK);
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.fillText(m_Message, 285, 260);

        drawBall(m_GameManager.GetBall(), graphics);
        drawPaddle(m_GameManager.GetPaddle(),graphics);

        for(Brick b : m_GameManager.GetWall().GetBricks()) {
            if (!b.IsBroken())
                drawBrick(b, graphics);
        }
    }
/*
        if(m_GameBoard.GetShowPauseMenu())
            m_PauseMenuPainter.DrawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();

    }
*/

    private void drawBrick(Brick brick,GraphicsContext g2d){

        g2d.setFill(brick.GetInnerColor());
        g2d.setStroke(brick.GetBorderColor());
        g2d.fillRect(brick.getLocation().getX(), brick.getLocation().getY(), 60,20);
        g2d.strokeRect(brick.getLocation().getX(), brick.getLocation().getY(), 60,20);
    }

    private void drawBall(Ball ball, GraphicsContext g2d)
    {
        g2d.setFill(ball.GetInnerColor());
        g2d.setStroke(ball.GetBorderColor());
        g2d.fillOval(ball.getLocation().getX(), ball.getLocation().getY(), 10,10);
        g2d.strokeOval(ball.getLocation().getX(), ball.getLocation().getY(), 10,10);
    }

    private void drawPaddle(Paddle paddle, GraphicsContext g2d){
        g2d.setFill(paddle.INNER_COLOR);
        g2d.setStroke(paddle.BORDER_COLOR);
        g2d.fillRect(paddle.getLocation().getX(), paddle.getLocation().getY(), 150,10);
        g2d.strokeRect(paddle.getLocation().getX(), paddle.getLocation().getY(), 150,10);
    }


}
