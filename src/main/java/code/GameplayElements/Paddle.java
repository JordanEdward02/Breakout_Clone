package code.GameplayElements;

import java.awt.*;


public class Paddle {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    public static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle paddleFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;

    public Shape GetPaddleFace(){
        return paddleFace;
    }

    public Paddle(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        paddleFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

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

    public void MoveTo(Point p){
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }
}
