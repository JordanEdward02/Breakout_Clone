package code.GameplayElements.Bricks;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class BrickSteel extends Brick {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    @Override
    public Shape GetBrick() {
        return brickFace;
    }

    public boolean SetImpact(Point2D point , int dir){
        if(super.IsBroken())
            return false;
        impact();
        return  super.IsBroken();
    }

    public BrickSteel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.m_brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    private void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.Impact();
        }
    }

}
