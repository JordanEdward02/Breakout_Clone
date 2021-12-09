package code.GameplayElements.Bricks;

import java.awt.*;
/**
 * Factory to create any type of brick
 * @author Jordan Lovett
 */
public class BrickFactory {
    /**
     * Creates a brick based on the parameters
     * @param point Point top left corner of the brick
     * @param size Dimension size of the brick
     * @param type Int type of brick to be created
     * @return Brick created by the factory
     */
    public static Brick GetBrick(Point point, Dimension size, int type)
    {
        Brick newBrick;
        switch(type)
        {
            case 0:
                return null;
            case 1:
                newBrick = new BrickClay(point,size);
                break;
            case 2:
                newBrick = new BrickSteel(point,size);
                break;
            case 3:
                newBrick = new BrickCement(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));

        }
        return newBrick;
    }
}
