package code.Menu.Painters;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


public class GameBoardPainter {

    private static double TEXT_RATIO_X = 0.475, TEXT_RATIO_Y = 0.5;
    private static double BALL_PADDLE_RATIO=60, PADDLE_RATIO_X = 4;

    private double m_CanvasSize;
    private Canvas m_GameBoard;
    private ElementsManager m_GameManager;
    private String m_Message;

    public void SetMessage(String newMessage)
    {
        m_Message = newMessage;
    }

    public GameBoardPainter(Canvas GameBoard, ElementsManager gameManager)
    {
        m_GameBoard = GameBoard;
        m_CanvasSize = m_GameBoard.getWidth();
        m_GameManager = gameManager;
        m_Message = "Press SPACE to start";
    }

    public void Refresh() {
        GraphicsContext graphics = m_GameBoard.getGraphicsContext2D();
        graphics.clearRect(0, 0, m_CanvasSize, m_CanvasSize);

        graphics.setFill(Color.BLACK);
        graphics.setStroke(Color.BLACK);
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.fillText(m_Message, m_CanvasSize*TEXT_RATIO_X,
                m_CanvasSize*TEXT_RATIO_Y);

        drawBall(m_GameManager.GetBall(), graphics);
        drawPaddle(m_GameManager.GetPaddle(),graphics);

        for(Brick b : m_GameManager.GetWall().GetBricks()) {
            if (!b.IsBroken())
                drawBrick(b, graphics);
        }
    }

    private void drawBrick(Brick brick,GraphicsContext g2d){

        g2d.setFill(brick.GetInnerColor());
        g2d.setStroke(brick.GetBorderColor());
        g2d.fillRect(brick.getLocation().getX(), brick.getLocation().getY(),
                brick.GetWidth(),brick.GetHeight());
        g2d.strokeRect(brick.getLocation().getX(), brick.getLocation().getY(),
                brick.GetWidth(),brick.GetHeight());
    }

    private void drawBall(Ball ball, GraphicsContext g2d)
    {
        g2d.setFill(ball.GetInnerColor());
        g2d.setStroke(ball.GetBorderColor());
        g2d.fillOval(ball.getLocation().getX(), ball.getLocation().getY(),
                m_CanvasSize/BALL_PADDLE_RATIO,m_CanvasSize/BALL_PADDLE_RATIO);
        g2d.strokeOval(ball.getLocation().getX(), ball.getLocation().getY(),
                m_CanvasSize/BALL_PADDLE_RATIO,m_CanvasSize/BALL_PADDLE_RATIO);
    }

    private void drawPaddle(Paddle paddle, GraphicsContext g2d){
        g2d.setFill(paddle.INNER_COLOR);
        g2d.setStroke(paddle.BORDER_COLOR);
        g2d.fillRect(paddle.getLocation().getX(), paddle.getLocation().getY(),
                m_CanvasSize/PADDLE_RATIO_X,m_CanvasSize/BALL_PADDLE_RATIO);
        g2d.strokeRect(paddle.getLocation().getX(), paddle.getLocation().getY(),
                m_CanvasSize/PADDLE_RATIO_X,m_CanvasSize/BALL_PADDLE_RATIO);
    }


}
