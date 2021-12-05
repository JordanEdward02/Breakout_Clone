package code.GameplayElements.Bricks;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Point2D;


public class BrickCement extends Brick {
    private static final int CEMENT_STRENGTH = 2, BRICK_TYPE=2;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 20.0;

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
        super(point,size,CEMENT_STRENGTH, BRICK_TYPE);
        SetImageSource(SOURCE_X, SOURCE_Y);
    }

    public void Repair(){
        super.Repair();
        AntiCrack();
    }
}
