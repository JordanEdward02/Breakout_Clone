package code.GameplayElements;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class BallRubber extends Ball {

    private static final int TWO = 2;
    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = Color.web("0xFFD700");
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    public BallRubber(Point startPoint){
        super(startPoint, DEF_RADIUS, DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }
/*
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA / TWO);
        double y = center.getY() - (radiusB / TWO);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }

 */
}
