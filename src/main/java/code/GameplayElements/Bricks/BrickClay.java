package code.GameplayElements.Bricks;

import javafx.scene.paint.Color;
import java.awt.*;
import java.awt.Point;


public class BrickClay extends Brick {

    private static final Color DEF_INNER = Color.DARKRED.darker();
    private static final Color DEF_BORDER = Color.DARKRED.darker().darker();
    private static final int CLAY_STRENGTH = 1;
    private static final double SOURCE_X = 0.0, SOURCE_Y = 0.0;
    public BrickClay(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
        SetImageSource(SOURCE_X, SOURCE_Y);
    }

}
