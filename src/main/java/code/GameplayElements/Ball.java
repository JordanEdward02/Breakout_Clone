package code.GameplayElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

abstract public class Ball {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Point m_TopCorner;
    private int m_Radius;

    private Color m_border;
    private Color m_Inner;

    private int speedX;
    private int speedY;

    public Point getLocation()
    {
        return m_TopCorner;
    }

    public void setLocation(Point newPoint)
    {
        m_TopCorner = newPoint;
        CalculateBounds();
    }

    public void SetRadius(int radius)
    {
        m_Radius=radius;
    }

    public Point2D GetUp()
    {
        return up;
    }

    public Point2D GetDown()
    {
        return down;
    }

    public Point2D GetRight()
    {
        return right;
    }

    public Point2D GetLeft()
    {
        return left;
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
        speedX = x;
        speedY = y;
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
        speedX = s;
    }

    public void SetYSpeed(int s){
        speedY = s;
    }

    public int GetSpeedX(){
        return speedX;
    }

    public int GetSpeedY(){
        return speedY;
    }

    public Ball(Point startPoint, int radius, Color inner, Color border){
        SetBorderColor(border);
        SetInnerColor(inner);
        SetRadius(radius);
        MoveTo(startPoint);
        SetSpeedRandom();
    }

    private void CalculateBounds()
    {
        up.setLocation(getLocation().getX()+m_Radius, getLocation().getY());
        down.setLocation(getLocation().getX()+m_Radius, getLocation().getY()+(m_Radius*2));
        left.setLocation(getLocation().getX(), getLocation().getY()+m_Radius);
        right.setLocation(getLocation().getX()+(m_Radius*2), getLocation().getY()+m_Radius);
    }
/*
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    public void Move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / TWO)),(center.getY() - (h / TWO)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    public void ReverseX(){
        speedX *= -1;
    }

    public void ReverseY(){
        speedY *= -1;
    }
*/
    public void MoveTo(Point p){
        m_TopCorner = new Point(((int)p.getX()-15), ((int) p.getY()-10));
    }

}
