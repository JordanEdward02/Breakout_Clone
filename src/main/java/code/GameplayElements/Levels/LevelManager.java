package code.GameplayElements.Levels;

import code.GameplayElements.Bricks.Brick;
import code.GameplayElements.Bricks.BrickClay;
import code.GameplayElements.Bricks.BrickFactory;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class LevelManager
{
    private int m_level=0;

    private Brick[] ReadLevelFile(String File, Rectangle drawArea) {
        try {
            Brick[] out;
            int brickCount = 0, offSet=0, line=0;
            double BrickLn = 0.0, BrickHgt = 0.0;
            File levelFile = new File(File);
            Scanner myScanner = new Scanner(levelFile);
            while (myScanner.hasNextLine()) {
                String nextLine = myScanner.nextLine();
                brickCount += nextLine.length();
                BrickLn = drawArea.getWidth() / nextLine.length();
                BrickHgt = BrickLn / 3;
            }

            out = new Brick[brickCount];
            myScanner = new Scanner(levelFile);
            while (myScanner.hasNextLine()) {
                String BrickLine = myScanner.nextLine();
                Dimension brickSize = new Dimension((int) BrickLn, (int) BrickHgt);
                Point p = new Point();
                int i;
                for (i = 0; i < BrickLine.length(); i++) {
                    double x = (i % BrickLine.length()) * BrickLn;
                    x = (line % 2 == 0) ? x : (x - (BrickLn / 2));
                    double y = (line) * BrickHgt;
                    p.setLocation(x, y);
                    out[i + offSet] = BrickFactory.GetBrick(p, brickSize, BrickLine.charAt(i)-'0');
                }
                line+=1;
                offSet += i;
            }
            return out;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    public boolean HasNextLevel()
    {
        try
        {
            File fileCheck = new File("src/main/java/code/GameplayElements/Levels/Level" +(m_level+1)+ ".txt");
            new Scanner(fileCheck);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void IncrementLevel()
    {
        m_level++;
    }

    public Brick[] RenderWall(Rectangle drawArea){
        String nextLevel = "src/main/java/code/GameplayElements/Levels/Level"+m_level+".txt";
        return ReadLevelFile(nextLevel, drawArea);

    }

}
