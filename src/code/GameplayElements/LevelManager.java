package code.GameplayElements;

import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Bricks.BrickClay;
import code.GameplayElements.Bricks.BrickFactory;

import java.awt.*;

public class LevelManager
{
    public static final int LEVELS_COUNT = 4;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private int level=0;

    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = BrickFactory.GetBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new BrickClay(p,brickSize);
        }
        return tmp;

    }

    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        // if brickCount is not divisible by line count, brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  BrickFactory.GetBrick(p,brickSize,typeA) : BrickFactory.GetBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = BrickFactory.GetBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    public boolean HasNextLevel(){
        return level < LEVELS_COUNT;
    }

    public void IncrementLevel()
    {
        level++;
    }

    public Brick[] RenderWall(Rectangle drawArea, int brickCount)
    {
        double m_brickDimensionRatio = (double) 6/2;
        int lineCount = 3;
        switch(level)
        {
            case 1:
                return makeSingleTypeLevel(drawArea,brickCount,lineCount,m_brickDimensionRatio,CLAY);
            case 2:
                return makeChessboardLevel(drawArea,brickCount,lineCount,m_brickDimensionRatio,CLAY,CEMENT);
            case 3:
                return makeChessboardLevel(drawArea,brickCount,lineCount,m_brickDimensionRatio,CLAY,STEEL);
            case 4:
                return makeChessboardLevel(drawArea,brickCount,lineCount,m_brickDimensionRatio,STEEL,CEMENT);
            default :
                return null;
        }
    }

}
