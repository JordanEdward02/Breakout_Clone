package code.GameplayElements;

import java.awt.geom.Point2D;
import java.awt.*;
import java.util.Random;

public class ElementsManager
{
    private Wall m_gameWall = null;
    private Ball m_gameBall = null;
    private Paddle m_gamePaddle = null;
    private int m_ballCount;
    private boolean m_ballLost;
    private Point m_startPoint ;
    private Rectangle m_drawArea;

    public ElementsManager(Wall gameWall, Point ballPos, Rectangle drawArea)
    {
     m_gameWall = gameWall;
     m_gameBall = new Ball1((ballPos));
     m_gamePaddle = new Paddle((Point) ballPos.clone(),150,10, m_drawArea);
     m_ballCount = 3;
     m_ballLost = false;
     m_startPoint = new Point(300,430);
     m_drawArea = drawArea;
    }
    public Wall getWall()
    {
        return m_gameWall;
    }

    public void setBall(Ball gameBall)
    {
        m_gameBall = gameBall;
        return;
    }

    public Ball getBall()
    {
        return m_gameBall;
    }

    public void setPaddle(Paddle gamePaddle)
    {
        m_gamePaddle = gamePaddle;
        return;
    }

    public Paddle getPaddle()
    {
        return m_gamePaddle;
    }

    public int getBallCount()
    {
        return m_ballCount;
    }

    public void move()
    {
        m_gamePaddle.move();
        m_gameBall.move();
    }

    public void findImpacts()
    {
        if(m_gamePaddle.impact(m_gameBall)){
            m_gameBall.reverseY();
        }
        else if(m_gameWall.impactWall(m_gameBall)){
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            m_gameWall.reduceBrickCount();
        }
        else if(impactBorder()) {
            m_gameBall.reverseX();
        }
        else if(m_gameBall.getPosition().getY() < m_drawArea.getY()){
            m_gameBall.reverseY();
        }
        else if(m_gameBall.getPosition().getY() > m_drawArea.getY() + m_drawArea.getHeight()){
            m_gameWall.reduceBrickCount();
            m_ballLost = true;
        }
    }

    public boolean isBallLost()
    {
        return m_ballLost;
    }


    public boolean ballEnd()
    {
        return m_ballCount == 0;
    }


    public void wallReset()
    {
        for(Brick b : m_gameWall.getBricks())
            b.repair();
        m_gameWall.setBrickCount(m_gameWall.getBricks().length);
        m_ballCount = 3;
    }


    public void ballReset()
    {
        Random rnd = new Random();
        m_gamePaddle.moveTo(m_startPoint);
        m_gameBall.moveTo(m_startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        m_gameBall.setSpeed(speedX,speedY);
        m_ballLost = false;
    }


    public boolean impactBorder()
    {
        Point2D p = m_gameBall.getPosition();
        return ((p.getX() < m_drawArea.getX()) ||(p.getX() > (m_drawArea.getX() + m_drawArea.getWidth())));
    }


    public void setBallXSpeed(int s)
    {
        m_gameBall.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        m_gameBall.setYSpeed(s);
    }

    public void resetBallCount()
    {
        m_ballCount = 3;
    }


}
