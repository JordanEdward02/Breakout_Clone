package code.GameplayElements.Balls;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

abstract public class Ball {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;

    private Point m_TopCorner;
    private int m_Radius;

    private Color m_border;
    private Color m_Inner;

    private int m_speedX;
    private int m_speedY;

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

    public Color GetBorderColor(){
        return m_border;
    }

    public Color GetInnerColor(){
        return m_Inner;
    }

    public void SetBorderColor(Color color)
    {
        m_border = color;
    }

    public void SetInnerColor(Color color)
    {
        m_Inner = color;
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
            speedX = rnd.nextInt(FIVE) - TWO;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(THREE);
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
        return (new Point((int) getLocation().getX()+m_Radius, (int) getLocation().getY()));
    }

    public Point GetLeft()
    {
        return(new Point((int) getLocation().getX(), (int) getLocation().getY()+m_Radius));
    }

    public Point GetDown()
    {
        return (new Point((int) getLocation().getX()+m_Radius, (int) getLocation().getY()+(m_Radius*2)));
    }

    public Ball(Point startPoint, int radius, Color inner, Color border){
        SetBorderColor(border);
        SetInnerColor(inner);
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
        m_TopCorner = new Point(((int)p.getX()-15), ((int) p.getY()-10));
    }

}
