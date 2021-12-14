package code.GameplayElements;

/**
 * Manages the score of the current player. This is a singleton to so the integrity of the score is always maintained
 * @author Jordan Lovett
 */
public class ScoreManager {
    private static final int CLAY_BRICK=1, CEMENT_BRICK=2;
    private static final int STEEL_BRICK=3, LEVEL_COMPLETE=4;
    private static ScoreManager m_ScoreManager;
    private int m_TotalScore=0;

    /**
     * Sets the score to default value of 0
     */
    public void SetDefault()
    {
        m_TotalScore=0;
    }

    /**
     * Get the score
     * @return Int representing the total score
     */
    public int GetScoreTotal()
    {
        return m_TotalScore;
    }

    /**
     * Get the singleton of the ScoreManager
     * @return ScoreManager object
     */
    public static ScoreManager GetScoreManager()
    {
        if (m_ScoreManager == null)
            m_ScoreManager = new ScoreManager();
        return m_ScoreManager;
    }

    /**
     * Increase the internal score base on the input type
     * @param type Int representing the type to increase the score by
     */
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

    /**
     * Changes the score for when a ball is lost
     */
    public void BallLost()
    {
        m_TotalScore -= 1000;
    }
}
