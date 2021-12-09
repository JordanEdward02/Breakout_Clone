package code.GameplayElements.Balls;

import javafx.scene.canvas.Canvas;
import java.awt.*;

/**
 * Represents a rubber ball
 * @author Jordan Lovett - modified
 */
public class BallRubber extends Ball {

    private static final int BALL_RADIUS=60;
    private static final double BALL_X = 30.0, BALL_Y = 0.0;

    /**
     * Constructor for making a rubber ball
     * @param startPoint Point to start the ball at
     * @param drawArea Canvas to draw the ball on
     */
    public BallRubber(Point startPoint, Canvas drawArea){
        super(startPoint, (int)drawArea.getWidth()/BALL_RADIUS);
        SetImageSource(BALL_X, BALL_Y);
    }
}
