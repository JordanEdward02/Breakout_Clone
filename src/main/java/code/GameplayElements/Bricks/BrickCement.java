package code.GameplayElements.Bricks;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class BrickCement extends Brick {
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    @Override
    public boolean SetImpact(Point2D point, int dir) {
        if(super.IsBroken())
            return false;
        super.Impact();
        if(!super.IsBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    @Override
    public Shape GetBrick() {
        return brickFace;
    }

    public BrickCement(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.m_brickFace;
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }


    private void updateBrick(){
        if(!super.IsBroken()){
            GeneralPath gp = crack.Draw();
            gp.append(super.m_brickFace,false);
            brickFace = gp;
        }
    }

    public void Repair(){
        super.Repair();
        crack.Reset();
        brickFace = super.m_brickFace;
    }
}
