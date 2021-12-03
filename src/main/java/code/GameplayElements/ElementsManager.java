package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Balls.BallRubber;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Levels.LevelManager;
import code.Menu.SFXPlayer;
import code.Menu.ScoreManager;
import javafx.scene.canvas.Canvas;
import java.awt.*;

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

    public Wall GetWall()
    {
        return m_gameWall;
    }

    public Ball GetBall()
    {
        return m_gameBall;
    }

    public Paddle GetPaddle()
    {
        return m_gamePaddle;
    }

    public int GetBallCount()
    {
        return m_ballCount;
    }

    public int GetBrickCount()
    {
        return m_gameWall.GetBrickCount();
    }

    public void SetBallXSpeed(int s)
    {
        m_gameBall.SetXSpeed(s);
    }

    public void SetBallYSpeed(int s){
        m_gameBall.SetYSpeed(s);
    }

    public ElementsManager(Wall gameWall, Canvas drawArea)
    {
        m_gameWall = gameWall;
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
    }

    public void Move()
    {
        m_gamePaddle.Move();
        m_gameBall.Move();
    }

    public void FindImpacts()
    {
        if(m_gamePaddle.Impact(m_gameBall)){
            SFXPlayer.CollisionSFX();
            m_gameBall.ReverseY();
        }
        if(m_gameWall.ImpactWall(m_gameBall)){
            m_gameWall.ReduceBrickCount();
            if (m_gameWall.GetPowerupCounter() % 2 == 0)
                m_gameBall.RandomSpeedUp();
        }

        if(impactBorder()) {
            SFXPlayer.CollisionSFX();
            m_gameBall.ReverseX();
        }
        if(m_gameBall.getLocation().getY() < 0){
            SFXPlayer.CollisionSFX();
            m_gameBall.ReverseY();
        }
        if(m_gameBall.getLocation().getY() > m_drawArea.getHeight()){
            m_ballCount--;
            m_ballLost = true;
            m_ScoreManager.BallLost();
        }
    }

    public boolean IsBallLost()
    {
        return m_ballLost;
    }

    public boolean BallEnd()
    {
        return m_ballCount == 0;
    }

    public void WallReset()
    {
        for(Brick b : m_gameWall.GetBricks())
            b.Repair();
        m_gameWall.SetBrickCount(m_gameWall.GetBricks().length);
        m_ballCount = THREE;
    }

    public void BallReset()
    {
        m_gameBall.MoveTo(m_startPoint);
        m_gameBall.SetSpeedDefault();
        m_ballLost = false;
    }

    public void PaddleReset()
    {
        m_gamePaddle.MoveTo(new Point((int) m_startPoint.getX()-90,(int) m_startPoint.getY()-5));
    }

    private boolean impactBorder()
    {
        Point p = m_gameBall.getLocation();
        return ((p.getX() < 0) || ( p.getX()+(m_gameBall.GetRadius()*2) > (m_drawArea.getWidth())));
    }

    public void ResetBallCount()
    {
        m_ballCount = THREE;
    }

    public boolean NewLevel()
    {
        return m_levelManager.HasNextLevel();
    }

    public void NextLevel() {
        m_levelManager.IncrementLevel();
        m_gameWall.RenderWall(m_levelManager, m_drawArea);
    }

    public void RenderLevel()
    {
        m_gameWall.RenderWall(m_levelManager, m_drawArea);
    }

    public void LevelSkip()
    {
        if (m_levelManager.HasNextLevel()) {
            BallReset();
            PaddleReset();
            NextLevel();
        }
    }
}
