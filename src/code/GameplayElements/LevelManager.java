package code.GameplayElements;

public class LevelManager
{
    public static final int LEVELS_COUNT = 4;
    private Brick[][] levels;
    private int level;

    levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
    level = 0;

}
