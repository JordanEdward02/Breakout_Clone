package code.GameplayElements.Bricks;

import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.Point;


public class BrickClay extends Brick {

    private static final Color DEF_INNER = Color.GRAY.darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    @Override
    public Shape GetBrick() {
        return super.m_brickFace;
    }

    public BrickClay(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

}
