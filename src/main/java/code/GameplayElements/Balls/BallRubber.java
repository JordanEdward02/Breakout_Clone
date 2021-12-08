package code.GameplayElements.Balls;

import javafx.scene.canvas.Canvas;
import java.awt.*;

public class BallRubber extends Ball {

    private static final int BALL_RADIUS=60;
    private static final double BALL_X = 30.0, BALL_Y = 0.0;

    public BallRubber(Point startPoint, Canvas drawArea){
        super(startPoint, (int)drawArea.getWidth()/BALL_RADIUS);
        SetImageSource(BALL_X, BALL_Y);
    }
}
