package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Levels.LevelManager;
import javafx.scene.canvas.Canvas;

/**
 * Represents the wall of bricks
 */
public class Wall {

    private Brick[] m_Bricks;

    private int m_BrickCount;
    private int m_PowerupCounter=0;

    /**
     * Gets the array of bricks
     * @return Brick[] array of all the bricks that make up the wall
     */
    public Brick[] GetBricks()
    {
        return m_Bricks;
    }

    /**
     * Sets the brick count
     * @param brickCount Int to set the brick count too
     */
    public void SetBrickCount(int brickCount)
    {
        m_BrickCount = brickCount;
    }

    /**
     * Gets the brick count
     * @return Int number of bricks left
     */
    public int GetBrickCount(){
        return m_BrickCount;
    }

    /**
     * Gets the counter for the powerup chance (every brick broke)
     * @return Int powerup counter for each brick broken
     */
    public int GetPowerupCounter()
    {
        return m_PowerupCounter;
    }

    /**
     * Decrements the brick count
     */
    public void ReduceBrickCount()
    {
        m_BrickCount--;
        m_PowerupCounter++;
    }

    /**
     * Increments the brick count
     */
    public void IncrementBrickCount()
    {
        m_BrickCount+=1;
    }

    /**
     * Renders the Brick[] of wall
     * @param levelManager The levelManager to read the level to render
     * @param drawArea Canvas to draw the wall on
     */
    public void RenderWall(LevelManager levelManager, Canvas drawArea){
        m_Bricks = levelManager.RenderWall(drawArea);
        m_BrickCount=0;
        for (Brick b : m_Bricks) {
            if (b!=null)
                m_BrickCount +=1;
        }
    }

    /**
     * Checks each brick that makes up the wall to check if the ball collided with it
     * @param ball Ball to check collisions with
     * @return Boolean if the ball collided with this wall
     */
    public boolean ImpactWall(Ball ball){
        for(Brick b : m_Bricks){
            if (b!=null) {
                switch (b.FindImpact(ball)) {//Horizontal Impact
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
        }
        return false;
    }

    /**
     * Checks if the wall is finished
     * @return Boolean if the wall is finished or not
     */
    public boolean IsDone(){
        return m_BrickCount == 0;
    }

}
