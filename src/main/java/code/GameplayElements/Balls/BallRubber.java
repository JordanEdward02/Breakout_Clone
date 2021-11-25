package code.GameplayElements.Balls;

import javafx.scene.paint.Color;

import java.awt.*;

public class BallRubber extends Ball {

    private static final int DEF_RADIUS = 5;
    private static final Color DEF_INNER_COLOR = Color.web("0xFFD700");
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    public BallRubber(Point startPoint){
        super(startPoint, DEF_RADIUS, DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }
}
