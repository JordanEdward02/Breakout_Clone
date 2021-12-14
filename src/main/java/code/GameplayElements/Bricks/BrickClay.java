package code.GameplayElements.Bricks;

import java.awt.*;
import java.awt.Point;

/**
 * Represents a Clay brick
 * @author Jordan Lovett - modified
 */
public class BrickClay extends Brick {

    private static final int CLAY_STRENGTH = 1, BRICK_TYPE=1;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 0.0;

    /**
     * Constructor for a clay brick
     * @param point Point for the top left corner of thr brick
     * @param size Dimension size of the brick
     */
    public BrickClay(Point point, Dimension size){
        super(point,size,CLAY_STRENGTH, BRICK_TYPE);
        SetImageSource(SOURCE_X, SOURCE_Y);
    }

}
