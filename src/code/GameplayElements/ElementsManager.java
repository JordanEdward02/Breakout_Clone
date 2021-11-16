package code.GameplayElements;

import code.GameplayElements.Bricks.Brick;

import java.awt.geom.Point2D;
import java.awt.*;
import java.util.Random;

public class ElementsManager
{
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int TWO = 2;
    private static final int PADDLE_X = 150;
    private static final int PADDLE_Y = 10;
    private static final int START_POINT_X = 300;
    private static final int START_POINT_Y = 430;

    private Wall m_gameWall;
    private Ball m_gameBall;
    private Paddle m_gamePaddle;
    private LevelManager m_levelManager;
    private int m_ballCount;
    private boolean m_ballLost;
    private Point m_startPoint ;
    private Rectangle m_drawArea;

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

    public ElementsManager(Wall gameWall, Point ballPos, Rectangle drawArea)
    {
        m_gameWall = gameWall;
        m_drawArea = drawArea;
        m_gameBall = new BallRubber((ballPos));
        m_gamePaddle = new Paddle((Point) ballPos.clone(),PADDLE_X,PADDLE_Y, m_drawArea);
        m_levelManager = new LevelManager();
        m_ballCount = THREE;
        m_ballLost = false;
        m_startPoint = new Point(START_POINT_X,START_POINT_Y);
    }

    public void Move()
    {
        m_gamePaddle.Move();
        m_gameBall.Move();
    }

    public void FindImpacts()
    {
        if(m_gamePaddle.Impact(m_gameBall)){
            m_gameBall.ReverseY();
        }
        else if(m_gameWall.ImpactWall(m_gameBall)){
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            m_gameWall.ReduceBrickCount();
        }
        else if(impactBorder()) {
            m_gameBall.ReverseX();
        }
        else if(m_gameBall.GetPosition().getY() < m_drawArea.getY()){
            m_gameBall.ReverseY();
        }
        else if(m_gameBall.GetPosition().getY() > m_drawArea.getY() + m_drawArea.getHeight()){
            m_ballCount--;
            m_ballLost = true;
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
        m_gamePaddle.MoveTo(m_startPoint);
        m_gameBall.MoveTo(m_startPoint);
        m_gameBall.SetSpeedRandom();
        m_ballLost = false;
    }

    private boolean impactBorder()
    {
        Point2D p = m_gameBall.GetPosition();
        return ((p.getX() < m_drawArea.getX()) ||(p.getX() > (m_drawArea.getX() + m_drawArea.getWidth())));
    }

    public void ResetBallCount()
    {
        m_ballCount = THREE;
    }

    public boolean NewLevel()
    {
        return m_levelManager.HasNextLevel();
    }

    public void NextLevel(){
        m_levelManager.IncrementLevel();
        m_gameWall.RenderWall(m_levelManager, m_drawArea);
    }

    public void StopPaddle()
    {
        m_gamePaddle.Stop();
    }
}
