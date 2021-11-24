package code.GameplayElements.Bricks;

import code.GameplayElements.Ball;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class Brick  {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random m_rnd;

    Shape m_brickFace;

    private Point m_BrickPoint;

    private Color m_border;
    private Color m_inner;

    private int m_fullStrength;
    private int m_strength;

    private boolean m_broken;

    public Point getLocation()
    {
        return m_BrickPoint;
    }

    public Color GetInnerColor()
    {
        return m_inner;
    }

    public Color GetBorderColor()
    {
        return m_border;
    }

    public  boolean SetImpact(Point2D point , int dir){
        if(m_broken)
            return false;
        Impact();
        return  m_broken;
    }

    public abstract Shape GetBrick();

    public Brick( Point pos,Dimension size,Color border,Color inner,int strength){
        m_rnd = new Random();
        m_BrickPoint = pos;
        m_broken = false;
        m_brickFace = makeBrickFace(pos,size);
        m_border = border;
        m_inner = inner;
        m_fullStrength = m_strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public void Crack()
    {
        m_inner = m_inner.darker().darker();
    }

    public void AntiCrack()
    {
        m_inner.brighter().brighter();
    }

    public final int FindImpact(Ball b){
        if(m_broken)
            return 0;
        int out  = 0;
        if(m_brickFace.contains(b.GetRight()))
            out = LEFT_IMPACT;
        else if(m_brickFace.contains(b.GetLeft()))
            out = RIGHT_IMPACT;
        else if(m_brickFace.contains(b.GetUp()))
            out = DOWN_IMPACT;
        else if(m_brickFace.contains(b.GetDown()))
            out = UP_IMPACT;
        return out;
    }

    public final boolean IsBroken(){
        return m_broken;
    }

    public void Repair() {
        m_broken = false;
        m_strength = m_fullStrength;
    }

    public void Impact(){
        m_strength--;
        m_broken = (m_strength == 0);
    }



}





