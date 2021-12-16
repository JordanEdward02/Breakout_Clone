package code.GameplayElements;


import code.GameplayElements.Balls.Ball;
import javafx.scene.canvas.Canvas;
import java.awt.*;

/**
 * Represents a paddle
 * @author Jordan Lovett - modified (but taken out another class {wall})
 */
public class Paddle {

    private static final int PADDLE_WIDTH = 4;
    private static final int PADDLE_HEIGHT = 60;
    private static final int PADDLE_X_OFFSET = 15;
    private static final int DEF_MOVE_AMOUNT = 5;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 60.0;

    private Point m_PaddlePoint;
    private int moveAmount;
    private double min=0;
    private double max=450;
    private double m_PaddleWidth, m_PaddleHeight;

    /**
     * Gets the location of the paddle
     * @return Point of the top left corner of the paddle
     */
    public Point getLocation()
    {
        return m_PaddlePoint;
    }

    /**
     * Gets the paddles x coordinate of the texture image
     * @return Double x coordinate of the image location
     */
    public double GetSourceX()
    {
        return SOURCE_X;
    }

    /**
     * Gets the paddle's y coordinate of the texture image
     * @return Double y coordinate of the image location
     */
    public double GetSourceY()
    {
        return SOURCE_Y;
    }

    /**
     * Gets the height of the paddle
     * @return Double height of the paddle
     */
    public double GetHeight()
    {
        return m_PaddleHeight;
    }

    /**
     * Gets the width of the paddle
     * @return Double paddle width
     */
    public double GetWidth()
    {
        return m_PaddleWidth;
    }

    /**
     * Constructor for making a paddle
     * @param point Point where the paddle should be drawn
     * @param drawArea Canvas to draw the paddle on
     */
    public Paddle(Point point, Canvas drawArea) {
        m_PaddleWidth = drawArea.getWidth()/PADDLE_WIDTH;
        m_PaddleHeight = drawArea.getWidth()/PADDLE_HEIGHT;
        m_PaddlePoint = new Point((int) (point.getX()-(m_PaddleWidth/2)-PADDLE_X_OFFSET),
                (int) (point.getY()-(m_PaddleHeight/2)));

    }

    /**
     * Check if the paddle had an impact with the ball
     * @param b Ball to check for collisions
     * @return Boolean if the paddle collided with the ball
     */
    public boolean Impact(Ball b){
        double ballCollision = b.getLocation().getX()+b.GetRadius();
        if (b.getLocation().getY() >= getLocation().getY() &&
                b.getLocation().getY()-10 <= getLocation().getY()){
            if (ballCollision >= getLocation().getX() && ballCollision <=
                    getLocation().getX() + 150) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the paddle based on speed and current location
     */
    public void Move(){
        double x = getLocation().getX() + moveAmount;
        if(x < min || x > max)
            return;
        m_PaddlePoint.setLocation(x, getLocation().getY());
    }

    /**
     * Makes the paddle's movement to left
     */
    public void MoveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Makes the paddle's movement to right
     */
    public void MoveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Sets the paddle's movement to be 0
     */
    public void Stop(){
        moveAmount = 0;
    }

    /**
     * Moves the paddle to the point
     * @param p Point to move the paddle too
     */
    public void MoveTo(Point p){
        m_PaddlePoint.setLocation(p);
    }
}
