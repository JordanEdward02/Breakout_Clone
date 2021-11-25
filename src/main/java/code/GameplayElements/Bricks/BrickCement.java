package code.GameplayElements.Bricks;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Point2D;


public class BrickCement extends Brick {
    private static final Color DEF_INNER = Color.GREY;
    private static final Color DEF_BORDER = Color.GREY.darker().darker();
    private static final int CEMENT_STRENGTH = 2;

    @Override
    public boolean SetImpact() {
        if(super.IsBroken())
            return false;
        super.Impact();
        if(!super.IsBroken()){
            Crack();
            return false;
        }
        return true;
    }


    public BrickCement(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
    }

    public void Repair(){
        super.Repair();
        AntiCrack();
    }
}
