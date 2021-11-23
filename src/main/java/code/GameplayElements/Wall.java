package code.GameplayElements;

import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Levels.LevelManager;
import javafx.scene.canvas.Canvas;


public class Wall {

    Brick[] m_Bricks;

    private int m_BrickCount = 30;

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


    public void ReduceBrickCount()
    {
        m_BrickCount--;
    }

    public void RenderWall(LevelManager levelManager, Canvas drawArea){
        m_Bricks = levelManager.RenderWall(drawArea);
        m_BrickCount = m_Bricks.length;
    }
    /*
    // Kept this in the wall class as it's about interactions with the wall, and this manages it with all the bricks
    public boolean ImpactWall(Ball ball){
        for(Brick b : m_Bricks){
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

     */


    public boolean IsDone(){
        return m_BrickCount == 0;
    }


}
