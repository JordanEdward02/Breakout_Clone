package code.Menu;

import code.GameplayElements.Bricks.Brick;

public class ScoreManager {
    private static ScoreManager m_ScoreManager;
    private int m_TotalScore=0;

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

    public void IncreaseScore(Brick b, int type)
    {
        switch(type){
            case 1:
                m_TotalScore+=100;
                break;
            case 2:
                m_TotalScore+=150;
                break;
            case 3:
                m_TotalScore+=200;
                break;
            default:
                break;
        }
    }

    public void BallLost()
    {
        m_TotalScore -= 2000;
    }
}
