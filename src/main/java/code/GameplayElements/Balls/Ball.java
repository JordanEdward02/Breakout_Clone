package code.GameplayElements.Balls;

import java.awt.*;
import java.util.Random;

abstract public class Ball {

    private static final int X_OFFSET = 15, Y_OFFSET = 10;

    private Point m_TopCorner;
    private int m_Radius;
    private int m_speedX;
    private int m_speedY;
    private double m_SourceX, m_SourceY;

    public double GetSourceX()
    {
        return m_SourceX;
    }

    public double GetSourceY()
    {
        return m_SourceY;
    }

    protected void SetImageSource(double sourceX, double sourceY)
    {
        m_SourceX = sourceX;
        m_SourceY = sourceY;
    }

    public Point getLocation()
    {
        return m_TopCorner;
    }

    public void setLocation(Point newPoint)
    {
        m_TopCorner = newPoint;
    }

    public void SetRadius(int radius)
    {
        m_Radius=radius;
    }

    public int GetRadius()
    {
        return m_Radius;
    }

    public void SetSpeed(int x,int y){
        m_speedX = x;
        m_speedY = y;
    }

    public void SetSpeedRandom()
    {
        Random rnd = new Random();

        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        SetSpeed(speedX,speedY);
    }

    public void SetXSpeed(int s){
        m_speedX = s;
    }

    public void SetYSpeed(int s){
        m_speedY = s;
    }

    public int GetSpeedX(){
        return m_speedX;
    }

    public int GetSpeedY(){
        return m_speedY;
    }

    public Point GetRight()
    {
        return (new Point((int) getLocation().getX()+m_Radius*2,(int) getLocation().getY()+m_Radius));
    }

    public Point GetUp()
    {
        return (new Point((int) getLocation().getX()+m_Radius,
                (int) getLocation().getY()));
    }

    public Point GetLeft()
    {
        return(new Point((int) getLocation().getX(),
                (int) getLocation().getY()+m_Radius));
    }

    public Point GetDown()
    {
        return (new Point((int) getLocation().getX()+m_Radius, (int) getLocation().getY()+(m_Radius*2)));
    }

    public Ball(Point startPoint, int radius){
        SetRadius(radius);
        MoveTo(startPoint);
        SetSpeedRandom();
    }

    public void Move(){
        int x = (int) getLocation().getX() + GetSpeedX();
        int y = (int) getLocation().getY() + GetSpeedY();
        setLocation(new Point(x,y));
    }

    public void ReverseX(){
        m_speedX *= -1;
    }

    public void ReverseY(){
        m_speedY *= -1;
    }

    public void MoveTo(Point p){
        m_TopCorner = new Point(((int)p.getX()-X_OFFSET),
                ((int) p.getY()-Y_OFFSET));
    }

}
