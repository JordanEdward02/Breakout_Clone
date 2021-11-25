package code.GameplayElements.Bricks;

import code.GameplayElements.Balls.Ball;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

abstract public class Brick  {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random m_rnd;

    private Point m_BrickPoint;

    private Color m_border;
    private Color m_inner;

    private int m_fullStrength;
    private int m_strength;

    private double m_Width;
    private double m_Height;

    private boolean m_broken;

    public Point getLocation()
    {
        return m_BrickPoint;
    }

    public double GetHeight()
    {
        return m_Height;
    }

    public double GetWidth()
    {
        return m_Width;
    }

    public Color GetInnerColor()
    {
        return m_inner;
    }

    public Color GetBorderColor()
    {
        return m_border;
    }

    public  boolean SetImpact(){
        if(m_broken)
            return false;
        Impact();
        return  m_broken;
    }

    public Brick( Point pos,Dimension size,Color border,Color inner,int strength){
        m_rnd = new Random();
        m_BrickPoint = pos;
        m_Width = size.width;
        m_Height = size.height;
        m_broken = false;
        m_border = border;
        m_inner = inner;
        m_fullStrength = m_strength = strength;

    }

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
        if(contains(b.GetRight()))
            out = LEFT_IMPACT;
        else if(contains(b.GetLeft()))
            out = RIGHT_IMPACT;
        else if(contains(b.GetUp()))
            out = DOWN_IMPACT;
        else if(contains(b.GetDown()))
            out = UP_IMPACT;
        return out;
    }

    public final boolean IsBroken(){
        return m_broken;
    }

    private boolean contains(Point point)
    {
        if (point.getX() >= this.getLocation().getX() && point.getX() <= this.getLocation().getX()+60)
            if (point.getY() >= this.getLocation().getY() && point.getY() <= this.getLocation().getY()+20)
                return true;
        return false;
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





