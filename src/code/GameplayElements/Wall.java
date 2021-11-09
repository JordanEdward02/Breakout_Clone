package code.GameplayElements;

import code.GameplayElements.Bricks.Brick;

import java.awt.*;

public class Wall {

    Brick[] bricks;

    private int m_brickCount = 31;

    public Brick[] getBricks()
    {
        return bricks;
    }

    public void reduceBrickCount()
    {
        m_brickCount--;
    }

    public void renderWall(LevelManager levelManager, Rectangle drawArea)
    {
        bricks = levelManager.renderWall(drawArea, m_brickCount);
    }
    // Kept this in the wall class as it's about interactions with the wall, and this manages it with all the bricks
    public boolean impactWall(Ball ball){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up,Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right,Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left,Brick.Crack.LEFT);
            }
        }
        return false;
    }

    public void setBrickCount(int brickCount)
    {
        m_brickCount = brickCount;
    }

    public int getBrickCount(){
        return m_brickCount;
    }


    public boolean isDone(){
        return m_brickCount == 0;
    }


}
