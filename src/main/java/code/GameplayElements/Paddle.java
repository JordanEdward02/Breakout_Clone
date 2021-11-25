package code.GameplayElements;


import code.GameplayElements.Balls.Ball;
import javafx.scene.paint.Color;

import java.awt.*;


public class Paddle {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;
    private static final int PADDLE_WIDTH = 150;
    private static final int PADDLE_HEIGHT = 10;

    public static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle paddleFace;
    private Point m_PaddlePoint;
    private int moveAmount;
    private double min=0;
    private double max=450;

    public Point getLocation()
    {
        return m_PaddlePoint;
    }


    public Paddle(Point point) {
        m_PaddlePoint = new Point((int) (point.getX()-(PADDLE_WIDTH/2)-15),(int) (point.getY()-(PADDLE_HEIGHT/2)));

    }
/*
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }
*/
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
