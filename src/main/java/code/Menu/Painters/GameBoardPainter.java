package code.Menu.Painters;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.Menu.ThemeMaintainer;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.io.File;


public class GameBoardPainter {

    private static double TEXT_RATIO_X = 0.475, TEXT_RATIO_Y = 0.5;
    private static double BALL_PADDLE_RATIO=60, PADDLE_RATIO_X = 4;
    private static double BRICK_X = 30, BRICK_Y = 10;
    private static double PADDLE_X = 60, PADDLE_Y = 4;
    private static double BALL_SIZE = 10;

    private double m_CanvasSize;
    private Canvas m_GameBoard;
    private ElementsManager m_GameManager;
    public Label m_GameInfo;

    public void SetMessage(String newMessage)
    {
        m_GameInfo.setText(newMessage);
    }

    public GameBoardPainter(Canvas GameBoard, ElementsManager gameManager, Label GameInfo)
    {
        m_GameBoard = GameBoard;
        m_GameInfo = GameInfo;
        m_CanvasSize = m_GameBoard.getWidth();
        m_GameManager = gameManager;
        SetMessage("Press SPACE to start");
    }

    public void Refresh() {
        GraphicsContext graphics = m_GameBoard.getGraphicsContext2D();
        graphics.clearRect(0, 0, m_CanvasSize, m_CanvasSize);

        drawBall(m_GameManager.GetBall(), graphics);
        drawPaddle(m_GameManager.GetPaddle(),graphics);

        for(Brick b : m_GameManager.GetWall().GetBricks()) {
            if (!b.IsBroken())
                drawBrick(b, graphics);
        }
    }

    private void drawBrick(Brick brick,GraphicsContext g2d)
    {
        g2d.drawImage(ThemeMaintainer.GetTexture(), brick.GetSourceX(), brick.GetSourceY(),
                BRICK_X, BRICK_Y, brick.getLocation().getX(),
                brick.getLocation().getY(), brick.GetWidth(), brick.GetHeight());
    }

    private void drawBall(Ball ball, GraphicsContext g2d)
    {
        g2d.drawImage(ThemeMaintainer.GetTexture(), ball.GetSourceX(), ball.GetSourceY(),
                BALL_SIZE, BALL_SIZE, ball.getLocation().getX(),
                ball.getLocation().getY(), ball.GetRadius(), ball.GetRadius());

    }

    private void drawPaddle(Paddle paddle, GraphicsContext g2d)
    {
        g2d.drawImage(ThemeMaintainer.GetTexture(), paddle.GetSourceX(), paddle.GetSourceY(),
                BALL_SIZE, BALL_SIZE, paddle.getLocation().getX(),
                paddle.getLocation().getY(), paddle.GetWidth(), paddle.GetHeight());
    }


}
