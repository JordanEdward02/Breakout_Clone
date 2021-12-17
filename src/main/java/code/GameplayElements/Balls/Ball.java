package code.GameplayElements.Balls;

import java.awt.*;
import java.util.Random;

/**
 * Represents a ball
 * @author Jordan Lovett - modified
 */
abstract public class Ball {

    private static final int X_OFFSET = 15, Y_OFFSET = 10;

    private Point m_TopCorner;
    private int m_Radius;
    private int m_speedX;
    private int m_speedY;
    private double m_SourceX, m_SourceY;
    private Random m_Rnd;

    /**
     * Gets the balls X coordinate from the image file
     * @return Double that represents the X location in the textures image
     */
    public double GetSourceX()
    {
        return m_SourceX;
    }

    /**
     * Gets the balls Y coordinate from the image file
     * @return Double that represents the Y location in the textures image
     */
    public double GetSourceY()
    {
        return m_SourceY;
    }

    /**
     * Sets the source location of the brick in the textures image
     * @param sourceX X coordinate in the textures image
     * @param sourceY Y coordinate in the textures image
     */
    protected void SetImageSource(double sourceX, double sourceY)
    {
        m_SourceX = sourceX;
        m_SourceY = sourceY;
    }

    /**
     * Gets the location of the ball
     * @return Point at the top left corner of the ball
     */
    public Point getLocation()
    {
        return m_TopCorner;
    }

    /**
     * Sets the point of the top left corner of the ball
     * @param newPoint Point that represents the top left corner of the ball
     */
    public void setLocation(Point newPoint)
    {
        m_TopCorner = newPoint;
    }

    /**
     * Sets the radius of the ball
     * @param radius
     */
    public void SetRadius(int radius)
    {
        m_Radius=radius;
    }

    /**
     * Gets the radius of the ball
     * @return Int that represents the radius of the ball
     */
    public int GetRadius()
    {
        return m_Radius;
    }

    /**
     * Pseudo-randomly increases the speed of the ball up to top speed of 5px
     */
    public void RandomSpeedUp()
    {
        if (m_Rnd.nextBoolean())
            if (m_speedX<0 && m_speedX>-5)
                m_speedX-=1;
            else if (m_speedX<5 && m_speedX>0)
                m_speedX+=1;
        else
            if (m_speedY>0 && m_speedY < 5)
                m_speedY += 1;
            else if (m_speedY <0 && m_speedY>-5)
                m_speedY -= 1;
    }

    /**
     * Sets the speed of the ball to the default (2px)
     */
    public void SetSpeedDefault()
    {
        m_speedY = -2;
        do {
            m_speedX = (m_Rnd.nextInt(3) - 1) * 2;
        } while (m_speedX==0);
    }

    /**
     * Sets the x Speed of the ball
     * @param s Int of the new x speed
     */
    public void SetXSpeed(int s){
        m_speedX = s;
    }

    /**
     * Sets the y speed of the ball
     * @param s Int of the new y speed
     */
    public void SetYSpeed(int s){
        m_speedY = s;
    }

    /**
     * Returns the balls current x speed
     * @return Int of the balls x speed
     */
    public int GetSpeedX(){
        return m_speedX;
    }

    /**
     * Returns the balls current y speed
     * @return Int of the balls y speed
     */
    public int GetSpeedY(){
        return m_speedY;
    }

    /**
     * Get the right point of the ball
     * @return Point on the right side of the ball
     */
    public Point GetRight()
    {
        return (new Point((int) getLocation().getX()+m_Radius*2,(int) getLocation().getY()+m_Radius));
    }

    /**
     * Get the top point of the ball
     * @return Point at the Top of the ball
     */
    public Point GetUp()
    {
        return (new Point((int) getLocation().getX()+m_Radius,
                (int) getLocation().getY()));
    }

    /**
     * Get the left side point of the ball
     * @return Point on the left of the ball
     */
    public Point GetLeft()
    {
        return(new Point((int) getLocation().getX(),
                (int) getLocation().getY()+m_Radius));
    }

    /**
     * Get the bottom point of the ball
     * @return Point on the bottom of the ball
     */
    public Point GetDown()
    {
        return (new Point((int) getLocation().getX()+m_Radius, (int) getLocation().getY()+(m_Radius*2)));
    }

    /**
     * Constructor for initialising any ball
     * @param startPoint Point where the ball should start of the screen
     * @param radius Int for the radius of the ball
     */
    public Ball(Point startPoint, int radius){
        SetRadius(radius);
        MoveTo(startPoint);
        m_Rnd = new Random();
        SetSpeedDefault();
    }

    /**
     * Moves the position of the ball based on the current speed and position
     */
    public void Move(){
        int x = (int) getLocation().getX() + GetSpeedX();
        int y = (int) getLocation().getY() + GetSpeedY();
        setLocation(new Point(x,y));
    }

    /**
     * Inverts the X speed of the ball
     */
    public void ReverseX(){
        m_speedX *= -1;
    }

    /**
     * Inverts the Y speed of the ball
     */
    public void ReverseY(){
        m_speedY *= -1;
    }

    /**
     * Moves the ball to a point
     * @param p Point to move the ball too
     */
    public void MoveTo(Point p){
        m_TopCorner = new Point(((int)p.getX()-X_OFFSET),
                ((int) p.getY()-Y_OFFSET));
    }

}
