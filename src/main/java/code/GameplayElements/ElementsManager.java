package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Balls.BallRubber;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Levels.LevelManager;
import code.Menu.SFXPlayer;
import javafx.scene.canvas.Canvas;
import java.awt.*;

/**
 * Represents an interface for all the gameplay elements
 * @author Jordan Lovett
 */
public class ElementsManager
{
    private static final int THREE = 3;
    private int m_StartX;
    private int m_StartY;

    private Wall m_gameWall;
    private Ball m_gameBall;
    private Paddle m_gamePaddle;
    private LevelManager m_levelManager;
    private int m_ballCount;
    private boolean m_ballLost;
    private Point m_startPoint ;
    private Canvas m_drawArea;
    private ScoreManager m_ScoreManager;
    private SFXPlayer m_SFXPlayer;

    /**
     * Gets the wall
     * @return A Wall of all the bricks
     */
    public Wall GetWall()
    {
        return m_gameWall;
    }

    /**
     * Gets the ball
     * @return A Ball representing the game ball
     */
    public Ball GetBall()
    {
        return m_gameBall;
    }

    /**
     * Gets the paddle
     * @return A Paddle repersenting the game paddle
     */
    public Paddle GetPaddle()
    {
        return m_gamePaddle;
    }

    /**
     * Gets the total number of balls
     * @return Int number of balls left
     */
    public int GetBallCount()
    {
        return m_ballCount;
    }

    /**
     * Gets the number of bricks left
     * @return Int number of remaining bricks
     */
    public int GetBrickCount()
    {
        return m_gameWall.GetBrickCount();
    }

    /**
     * Sets the X speed of the ball
     * @param s Int speed to set for the ball
     */
    public void SetBallXSpeed(int s)
    {
        m_gameBall.SetXSpeed(s);
    }

    /**
     * Sets the Y speed of the ball
     * @param s Int speed to set for the ball
     */
    public void SetBallYSpeed(int s){
        m_gameBall.SetYSpeed(s);
    }

    /**
     * Constructor for the element manager that maintains all the gameplay parts
     * @param drawArea Canvas that all the game elements will be drawn on
     */
    public ElementsManager(Canvas drawArea)
    {
        m_gameWall = new Wall();
        m_drawArea = drawArea;
        m_StartX = (int) (m_drawArea.getHeight()*0.5);
        m_StartY = (int) (drawArea.getWidth()*0.8);
        m_startPoint = new Point(m_StartX,m_StartY);
        m_gameBall = new BallRubber(m_startPoint, drawArea);
        m_gamePaddle = new Paddle(m_startPoint, drawArea);
        m_levelManager = new LevelManager();
        m_ballCount = THREE;
        m_ballLost = false;
        m_ScoreManager = ScoreManager.GetScoreManager();
        m_SFXPlayer = new SFXPlayer();
    }

    /**
     * Moves the position of all the elements
     */
    public void Move()
    {
        m_gamePaddle.Move();
        m_gameBall.Move();
    }

    /**
     * Finds all the impacts between all the gameplay elements and figures out their impact logic
     */
    public void FindImpacts()
    {
        if(m_gamePaddle.Impact(m_gameBall)){
            m_SFXPlayer.CollisionSFX();
            m_gameBall.ReverseY();
        }
        if(m_gameWall.ImpactWall(m_gameBall)){
            m_gameWall.ReduceBrickCount();
            if (m_gameWall.GetPowerupCounter() % 2 == 0)
                m_gameBall.RandomSpeedUp();
        }

        if(impactBorder()) {
            m_SFXPlayer.CollisionSFX();
            m_gameBall.ReverseX();
        }
        if(m_gameBall.getLocation().getY() < 0){
            m_SFXPlayer.CollisionSFX();
            m_gameBall.ReverseY();
        }
        if(m_gameBall.getLocation().getY() > m_drawArea.getHeight()){
            m_ballCount--;
            m_ballLost = true;
        }
    }

    /**
     * Checks if the ball has been lost
     * @return Boolean of if the ball is lost
     */
    public boolean IsBallLost()
    {
        return m_ballLost;
    }

    /**
     * Checks if there are balls left
     * @return Boolean of if there are any balls left
     */
    public boolean BallEnd()
    {
        return m_ballCount == 0;
    }

    /**
     * Resets the wall by repairing all the bricks and setting the brick count back
     */
    public void WallReset()
    {
        m_gameWall.SetBrickCount(0);
        for(Brick b : m_gameWall.GetBricks())
            if (b!=null) {
                b.Repair();
                m_gameWall.IncrementBrickCount();
            }
        m_ballCount = THREE;
    }

    /**
     * Resets the ball position and speed to the start
     */
    public void BallReset()
    {
        m_gameBall.MoveTo(m_startPoint);
        m_gameBall.SetSpeedDefault();
        m_ballLost = false;
    }

    /**
     * Resets the paddle position to the start
     */
    public void PaddleReset()
    {
        m_gamePaddle.MoveTo(new Point((int) m_startPoint.getX()-90,(int) m_startPoint.getY()-5));
    }

    private boolean impactBorder()
    {
        Point p = m_gameBall.getLocation();
        return ((p.getX() < 0) || ( p.getX()+(m_gameBall.GetRadius()*2) > (m_drawArea.getWidth())));
    }

    /**
     * Resets the ball count back to max (3)
     */
    public void ResetBallCount()
    {
        m_ballCount = THREE;
    }

    /**
     * Checks the level manager to see if there's a new level
     * @return Boolean if there's a next level
     */
    public boolean NewLevel()
    {
        return m_levelManager.HasNextLevel();
    }

    /**
     * Progresses game to the next level
     */
    public void NextLevel() {
        m_levelManager.IncrementLevel();
        m_gameWall.RenderWall(m_levelManager, m_drawArea);
    }

    /**
     * Renders all the bricks in the wall
     */
    public void RenderLevel()
    {
        m_gameWall.RenderWall(m_levelManager, m_drawArea);
    }

    /**
     * Skips the game to the next level and resets all the game parts
     */
    public void LevelSkip()
    {
        if (m_levelManager.HasNextLevel()) {
            BallReset();
            PaddleReset();
            NextLevel();
        }
    }
}
