package code.GameplayElements.Bricks;

import java.awt.*;

/**
 * Represents a cement brick
 * @author Jordan Lovett - modified
 */
public class BrickCement extends Brick {
    private static final int CEMENT_STRENGTH = 2, BRICK_TYPE=2;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 20.0;

    /**
     * Overrides the brick impact to crack when first hit instead of just breaking
     * @return Boolean if the brick is broken upon an impact
     */
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

    /**
     * Constructor for cement brick
     * @param point Point for the top left location of the brick
     * @param size Dimension size of the brick
     */
    public BrickCement(Point point, Dimension size){
        super(point,size,CEMENT_STRENGTH, BRICK_TYPE);
        SetImageSource(SOURCE_X, SOURCE_Y);
    }

    /**
     * Fixes the brick to full strength
     */
    public void Repair(){
        super.Repair();
        AntiCrack();
    }
}
