package code.GameplayElements;


import code.GameplayElements.Balls.Ball;
import javafx.scene.canvas.Canvas;
import java.awt.*;


public class Paddle {

    private static final int PADDLE_WIDTH = 4;
    private static final int PADDLE_HEIGHT = 60;
    private static final int PADDLE_X_OFFSET = 15;
    public static final int DEF_MOVE_AMOUNT = 5;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 60.0;

    private Point m_PaddlePoint;
    private int moveAmount;
    private double min=0;
    private double max=450;
    private double m_PaddleWidth, m_PaddleHeight;

    public Point getLocation()
    {
        return m_PaddlePoint;
    }

    public double GetSourceX()
    {
        return SOURCE_X;
    }

    public double GetSourceY()
    {
        return SOURCE_Y;
    }

    public double GetHeight()
    {
        return m_PaddleHeight;
    }

    public double GetWidth()
    {
        return m_PaddleWidth;
    }

    public Paddle(Point point, Canvas drawArea) {
        m_PaddleWidth = drawArea.getWidth()/PADDLE_WIDTH;
        m_PaddleHeight = drawArea.getWidth()/PADDLE_HEIGHT;
        m_PaddlePoint = new Point((int) (point.getX()-(m_PaddleWidth/2)-PADDLE_X_OFFSET),
                (int) (point.getY()-(m_PaddleHeight/2)));

    }

    public boolean Impact(Ball b){
        double ballCollision = b.getLocation().getX()+b.GetRadius();
        if (b.getLocation().getY() >= getLocation().getY() && b.getLocation().getY()-10 <= getLocation().getY()){
            if (ballCollision >= getLocation().getX() && ballCollision <= getLocation().getX() + 150) {
                return true;
            }
        }
        return false;
    }

    public void Move(){
        double x = getLocation().getX() + moveAmount;
        if(x < min || x > max)
            return;
        m_PaddlePoint.setLocation(x, getLocation().getY());
    }

    public void MoveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void MoveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void Stop(){
        moveAmount = 0;
    }

    public void MoveTo(Point p){
        m_PaddlePoint.setLocation(p);
    }
}
