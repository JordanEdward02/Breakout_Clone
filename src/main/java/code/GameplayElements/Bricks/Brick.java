package code.GameplayElements.Bricks;

import code.GameplayElements.Balls.Ball;
import code.Menu.SFXPlayer;
import code.Menu.ScoreManager;
import java.awt.*;
import java.util.Random;

/**
 * Represents a brick
 * @author Jordan Lovett - modified
 */
abstract public class Brick  {
    /**
     * Set int to represent an impact with the top of the brick
     */
    public static final int UP_IMPACT = 100;
    /**
     * Set int to represent an impact with the bottom of the brick
     */
    public static final int DOWN_IMPACT = 200;
    /**
     * Set int to represent an impact with the left of the brick
     */
    public static final int LEFT_IMPACT = 300;
    /**
     * Set int to represent an impact with the right of the brick
     */
    public static final int RIGHT_IMPACT = 400;

    private static Random m_rnd;

    private Point m_BrickPoint;
    private int m_fullStrength;
    private int m_strength;
    private double m_Width;
    private double m_Height;
    private boolean m_broken;
    private double m_SourceX, m_SourceY;
    private ScoreManager m_ScoreManager;
    private int m_Type;
    private SFXPlayer m_SFXPlayer;

    /**
     * Get the to left coordinate of the brick
     * @return Point of the bricks location
     */
    public Point getLocation()
    {
        return m_BrickPoint;
    }

    /**
     * Get X source of the brick in the texture image
     * @return Double x coordinate in the texture image
     */
    public double GetSourceX()
    {
        return m_SourceX;
    }

    /**
     * Get Y source of the brick in the texture image
     * @return Double y coordinate in the texture image
     */
    public double GetSourceY()
    {
        return m_SourceY;
    }

    /**
     * Gets the height of a brick
     * @return Double height of a brick
     */
    public double GetHeight()
    {
        return m_Height;
    }

    /**
     * Gets the width of a brick
     * @return Double width of a brick
     */
    public double GetWidth()
    {
        return m_Width;
    }

    /**
     * Sets an impact on the brick
     * @return Boolean if the brick is broken by the impact
     */
    public boolean SetImpact(){
        if(m_broken)
            return false;
        Impact();
        return  m_broken;
    }

    /**
     * Sets the coordinates of the bricks texture location in the texture image
     * @param sourceX X coordinate of the brick texture
     * @param sourceY Y coordinate of the brick texture
     */
    protected void SetImageSource(double sourceX, double sourceY)
    {
        m_SourceX = sourceX;
        m_SourceY = sourceY;
    }

    /**
     * Constructor for a brick
     * @param pos Point location of the bricks top left corner
     * @param size Dimension of the brick
     * @param strength Int strength of the brick
     * @param type Int type of brick
     */
    public Brick( Point pos,Dimension size,int strength, int type){
        m_rnd = new Random();
        m_Type = type;
        m_BrickPoint = pos;
        m_Width = size.width;
        m_Height = size.height;
        m_broken = false;
        m_fullStrength = m_strength = strength;
        m_ScoreManager = ScoreManager.GetScoreManager();
        m_SFXPlayer = new SFXPlayer();
    }

    /**
     * Cracks the brick by swapping out the texture
     */
    public void Crack()
    {
        m_SourceY+=10.0;
    }

    /**
     * Reverses the cracking of a brick, settings its texture to default
     */
    public void AntiCrack()
    {
        if(m_SourceY!=20.0)
            m_SourceY-=10.0;
    }

    /**
     * Find an impact of the brick with the ball
     * @param b Ball to compare for collisions
     * @return Int set value for the impact side
     */
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

    /**
     * Checks if the brick is broken or not
     * @return Boolean if the brick has been broken or not
     */
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

    /**
     * Reverse a brick break and set its strength to max
     */
    public void Repair() {
        m_broken = false;
        m_strength = m_fullStrength;
    }

    /**
     * Calculates the changes when the brick is impacted and plays the impact sound
     */
    public void Impact(){
        m_SFXPlayer.CollisionSFX();
        m_strength--;
        m_broken = (m_strength == 0);
        if (m_broken)
            m_ScoreManager.IncreaseScore(m_Type);
    }
}





