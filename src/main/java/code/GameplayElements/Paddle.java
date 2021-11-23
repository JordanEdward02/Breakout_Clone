package code.GameplayElements;


import javafx.scene.canvas.Canvas;
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
    private double min;
    private double max;

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

    public boolean Impact(Ball b){
        return paddleFace.contains(b.GetPosition()) && paddleFace.contains(b.down) ;
    }

    public void Move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
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
*/
    public void MoveTo(Point p){
        m_PaddlePoint.setLocation(p);
    }
}
