package code.GameplayElements.Bricks;

import java.awt.*;

public class BrickFactory {

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
