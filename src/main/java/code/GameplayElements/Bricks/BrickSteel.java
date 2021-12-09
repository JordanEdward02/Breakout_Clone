package code.GameplayElements.Bricks;

import code.Menu.SFXPlayer;
import java.awt.*;
import java.util.Random;

/**
 * Represents a steel brick
 * @author Jordan Lovett - modified
 */
public class BrickSteel extends Brick {

    private static final int STEEL_STRENGTH = 1, BRICK_TYPE=3;
    private static final double STEEL_PROBABILITY = 0.4;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 10.0;

    private Random rnd;
    private SFXPlayer m_SFXPlayer;

    /**
     * Overrides the impact of the brick to be random chance the break rather than set
     * @return Boolean if the brick gets broken
     */
    @Override
    public boolean SetImpact(){
        if(super.IsBroken())
            return false;
        impact();
        return super.IsBroken();
    }

    /**
     * Constructor for a steel brick
     * @param point Point top left corner of the brick
     * @param size Dimension size of the brick
     */
    public BrickSteel(Point point, Dimension size){
        super(point,size,STEEL_STRENGTH, BRICK_TYPE);
        SetImageSource(SOURCE_X, SOURCE_Y);
        rnd = new Random();
        m_SFXPlayer = new SFXPlayer();
    }

    private void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.Impact();
        }
        else
            m_SFXPlayer.CollisionSFX();
    }

}
