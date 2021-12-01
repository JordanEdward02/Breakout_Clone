package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Levels.LevelManager;
import javafx.scene.canvas.Canvas;


public class Wall {

    Brick[] m_Bricks;

    private int m_BrickCount ;
    private int m_PowerupCounter=0;

    public Brick[] GetBricks()
    {
        return m_Bricks;
    }

    public void SetBrickCount(int brickCount)
    {
        m_BrickCount = brickCount;
    }

    public int GetBrickCount(){
        return m_BrickCount;
    }

    public int GetPowerupCounter()
    {
        return m_PowerupCounter;
    }

    public void ReduceBrickCount()
    {
        m_BrickCount--;
        m_PowerupCounter++;
    }

    public void RenderWall(LevelManager levelManager, Canvas drawArea){
        m_Bricks = levelManager.RenderWall(drawArea);
        m_BrickCount = m_Bricks.length;
    }

    // Kept this in the wall class as it's about interactions with the wall, and this manages it with all the bricks
    public boolean ImpactWall(Ball ball){
        for(Brick b : m_Bricks){
            switch(b.FindImpact(ball)) {//Horizontal Impact
                case Brick.LEFT_IMPACT:
                case Brick.RIGHT_IMPACT:
                    ball.ReverseX();
                    return b.SetImpact();

                //Vertical Impact
                case Brick.UP_IMPACT:
                case Brick.DOWN_IMPACT:
                    ball.ReverseY();
                    return b.SetImpact();

            }
        }
        return false;
    }

    public boolean IsDone(){
        return m_BrickCount == 0;
    }

}
