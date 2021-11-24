package code.GameplayElements.Bricks;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Point2D;


public class BrickCement extends Brick {
    private static final Color DEF_INNER = Color.GREY;
    private static final Color DEF_BORDER = Color.GREY.darker().darker();
    private static final int CEMENT_STRENGTH = 2;

    private Shape brickFace;

    @Override
    public boolean SetImpact(Point2D point, int dir) {
        if(super.IsBroken())
            return false;
        super.Impact();
        if(!super.IsBroken()){
            Crack();
            return false;
        }
        return true;
    }


    @Override
    public Shape GetBrick() {
        return brickFace;
    }

    public BrickCement(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        brickFace = super.m_brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }


    public void Repair(){
        super.Repair();
        AntiCrack();
        brickFace = super.m_brickFace;
    }
}
