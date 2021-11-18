package code.GameplayElements;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Random;

abstract public class Ball {

    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FIVE = 5;
    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

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
        return border;
    }

    public Color GetInnerColor(){
        return inner;
    }

    public Point2D GetPosition(){
        return center;
    }

    public Shape GetBallFace(){
        return ballFace;
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

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int GetSpeedX(){
        return speedX;
    }

    public int GetSpeedY(){
        return speedY;
    }

    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / TWO));
        down.setLocation(center.getX(),center.getY()+(radiusB / TWO));

        left.setLocation(center.getX()-(radiusA /TWO),center.getY());
        right.setLocation(center.getX()+(radiusA /TWO),center.getY());

        SetSpeedRandom();
        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
    }

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

    public void MoveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / TWO)),(center.getY() - (h / TWO)),w,h);
        ballFace = tmp;
    }


}
