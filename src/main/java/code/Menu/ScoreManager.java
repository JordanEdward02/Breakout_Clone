package code.Menu;

import code.GameplayElements.Bricks.Brick;

public class ScoreManager {
    private static final int CLAY_BRICK=1, CEMENT_BRICK=2;
    private static final int STEEL_BRICK=3, LEVEL_COMPLETE=4;
    private static ScoreManager m_ScoreManager;
    private int m_TotalScore=0;

    public void SetDefault()
    {
        m_TotalScore=0;
    }

    public int GetScoreTotal()
    {
        return m_TotalScore;
    }

    public static ScoreManager GetScoreManager()
    {
        if (m_ScoreManager == null)
            m_ScoreManager = new ScoreManager();
        return m_ScoreManager;
    }

    public void IncreaseScore(int type)
    {
        switch(type){
            case CLAY_BRICK:
                m_TotalScore+=100;
                break;
            case CEMENT_BRICK:
                m_TotalScore+=150;
                break;
            case STEEL_BRICK:
                m_TotalScore+=200;
                break;
            case LEVEL_COMPLETE:
                m_TotalScore+=1000;
                break;
            default:
                break;
        }
    }

    public void BallLost()
    {
        m_TotalScore -= 1000;
    }
}
