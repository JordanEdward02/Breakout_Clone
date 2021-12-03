package code.GameplayElements.Bricks;

import code.Menu.SFXPlayer;
import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class BrickSteel extends Brick {

    private static final Color DEF_INNER = Color.SILVER;
    private static final Color DEF_BORDER = Color.SILVER.darker().darker();
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 10.0;

    private Random rnd;

    @Override
    public boolean SetImpact(){
        if(super.IsBroken())
            return false;
        impact();
        return  super.IsBroken();
    }

    public BrickSteel(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        SetImageSource(SOURCE_X, SOURCE_Y);
        rnd = new Random();
    }

    private void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.Impact();
        }
        else
            SFXPlayer.CollisionSFX();
    }

}
