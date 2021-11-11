package code.GameplayElements;

import code.GameplayElements.Bricks.Brick;

import java.awt.*;

public class Wall {

    Brick[] bricks;

    private int m_brickCount = 31;

    public Brick[] GetBricks()
    {
        return bricks;
    }

    public void SetBrickCount(int brickCount)
    {
        m_brickCount = brickCount;
    }

    public int GetBrickCount(){
        return m_brickCount;
    }


    public void ReduceBrickCount()
    {
        m_brickCount--;
    }

    public void RenderWall(LevelManager levelManager, Rectangle drawArea)
    {
        bricks = levelManager.RenderWall(drawArea, m_brickCount);
    }
    // Kept this in the wall class as it's about interactions with the wall, and this manages it with all the bricks
    public boolean ImpactWall(Ball ball){
        for(Brick b : bricks){
            switch(b.FindImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.ReverseY();
                    return b.SetImpact(ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.ReverseY();
                    return b.SetImpact(ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.ReverseX();
                    return b.SetImpact(ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.ReverseX();
                    return b.SetImpact(ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }


    public boolean IsDone(){
        return m_brickCount == 0;
    }


}
