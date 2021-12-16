package code.Menu.Painters;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.ElementsManager;
import code.GameplayElements.Paddle;
import code.GameplayElements.ScoreManager;
import code.GameplayElements.ThemeMaintainer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

/**
 * Painter which draws all of the gameplay elements upon every refresh
 * @author Jordan Lovett
 */
public class GameBoardPainter {

    private static double BRICK_X = 30, BRICK_Y = 10;
    private static double BALL_SIZE = 10;

    private double m_CanvasSize;
    private Canvas m_GameBoard;
    private ElementsManager m_GameManager;
    private ThemeMaintainer m_ThemeMaintainer;
    private Label m_ScoreLabel;
    private ScoreManager m_ScoreManager;
    private Label m_GameInfo;

    /**
     * Sets the middle screen message
     * @param newMessage String to set the label too
     */
    public void SetMessage(String newMessage)
    {
        m_GameInfo.setText(newMessage);
    }

    /**
     * Main constructor to create a working instance of the painter
     * @param GameBoard Canvas to draw all the elements too
     * @param gameManager ElementsManager to get all the data for elements to draw
     * @param GameInfo Label to hold the middle screen text
     * @param ScoreLabel Label to hold the score text
     */
    public GameBoardPainter(Canvas GameBoard, ElementsManager gameManager, Label GameInfo, Label ScoreLabel)
    {
        m_ThemeMaintainer = ThemeMaintainer.GetThemeMaintainer();
        m_GameBoard = GameBoard;
        m_GameInfo = GameInfo;
        m_ScoreLabel = ScoreLabel;
        m_CanvasSize = m_GameBoard.getWidth();
        m_GameManager = gameManager;
        m_ScoreManager = ScoreManager.GetScoreManager();
        SetMessage("Press SPACE to start");
    }

    /**
     * Redraw the whole screen by clearing the full canvas and then redrawing all the elements and labels
     */
    public void Refresh() {
        GraphicsContext graphics = m_GameBoard.getGraphicsContext2D();
        graphics.clearRect(0, 0, m_CanvasSize, m_CanvasSize);
        m_ScoreLabel.setText(""+m_ScoreManager.GetScoreTotal());
        drawBall(m_GameManager.GetBall(), graphics);
        drawPaddle(m_GameManager.GetPaddle(),graphics);

        for(Brick b : m_GameManager.GetWall().GetBricks()) {
            if (b!=null && !b.IsBroken())
                drawBrick(b, graphics);
        }
    }

    private void drawBrick(Brick brick,GraphicsContext g2d)
    {
        if (brick == null)
            return;
        g2d.drawImage(m_ThemeMaintainer.GetTexture(), brick.GetSourceX(), brick.GetSourceY(),
                BRICK_X, BRICK_Y, brick.getLocation().getX(),
                brick.getLocation().getY(), brick.GetWidth(), brick.GetHeight());
    }

    private void drawBall(Ball ball, GraphicsContext g2d)
    {
        g2d.drawImage(m_ThemeMaintainer.GetTexture(), ball.GetSourceX(), ball.GetSourceY(),
                BALL_SIZE, BALL_SIZE, ball.getLocation().getX(),
                ball.getLocation().getY(), ball.GetRadius(), ball.GetRadius());

    }

    private void drawPaddle(Paddle paddle, GraphicsContext g2d)
    {
        g2d.drawImage(m_ThemeMaintainer.GetTexture(), paddle.GetSourceX(), paddle.GetSourceY(),
                BALL_SIZE, BALL_SIZE, paddle.getLocation().getX(),
                paddle.getLocation().getY(), paddle.GetWidth(), paddle.GetHeight());
    }


}
