package code.GameplayElements.Bricks;

import code.GameplayElements.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;



    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;



        private GeneralPath m_crack;

        private int m_crackDepth;
        private int m_steps;


        public Crack(int crackDepth, int steps){

            m_crack = new GeneralPath();
            this.m_crackDepth = crackDepth;
            this.m_steps = steps;

        }



        public GeneralPath Draw(){

            return m_crack;
        }

        public void Reset(){
            m_crack.reset();
        }

        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.m_brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)m_steps;
            double h = (end.y - start.y) / (double)m_steps;

            int bound = m_crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < m_steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,m_steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            m_crack.append(path,true);
        }

        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return m_rnd.nextInt(n) - bound;
        }

        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        private int jumps(int bound,double probability){

            if(m_rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch(direction){
                case HORIZONTAL:
                    pos = m_rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos,to.y);
                    break;
                case VERTICAL:
                    pos = m_rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,pos);
                    break;
            }
            return out;
        }

    }

    private static Random m_rnd;

    Shape m_brickFace;

    private Color m_border;
    private Color m_inner;

    private int m_fullStrength;
    private int m_strength;

    private boolean m_broken;

    public  boolean SetImpact(Point2D point , int dir){
        if(m_broken)
            return false;
        Impact();
        return  m_broken;
    }

    public abstract Shape GetBrick();

    public Brick( Point pos,Dimension size,Color border,Color inner,int strength){
        m_rnd = new Random();
        m_broken = false;
        m_brickFace = makeBrickFace(pos,size);
        m_border = border;
        m_inner = inner;
        m_fullStrength = m_strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public Color GetBorderColor(){
        return  m_border;
    }

    public Color GetInnerColor(){
        return m_inner;
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





