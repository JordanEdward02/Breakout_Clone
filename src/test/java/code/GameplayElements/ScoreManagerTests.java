package code.GameplayElements;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreManagerTests {
    private static ScoreManager m_ScoreManager;
    @BeforeAll
    static void beforeAll() {
        m_ScoreManager = new ScoreManager();
    }

    @Test
    void setDefault() {
        m_ScoreManager.SetDefault();
        assertEquals(0, m_ScoreManager.GetScoreTotal());
    }

    @Test
    void getScoreManager() {
        ScoreManager testScoreManager = ScoreManager.GetScoreManager();
        assertNotEquals(null, testScoreManager);
    }

    @Test
    void increaseScore() {
        m_ScoreManager = ScoreManager.GetScoreManager();
        m_ScoreManager.SetDefault();
        m_ScoreManager.IncreaseScore(1);
        if (m_ScoreManager.GetScoreTotal() != 100){
            fail("Score manager not working for type 1");
        }
        m_ScoreManager.SetDefault();
        m_ScoreManager.IncreaseScore(2);
        if (m_ScoreManager.GetScoreTotal() != 150){
            fail("Score manager not working for type 2");
        }
        m_ScoreManager.SetDefault();
        m_ScoreManager.IncreaseScore(3);
        if (m_ScoreManager.GetScoreTotal() != 200){
            fail("Score manager not working for type 3");
        }
        m_ScoreManager.SetDefault();
        m_ScoreManager.IncreaseScore(4);
        if (m_ScoreManager.GetScoreTotal() != 1000){
            fail("Score manager not working for type 4");
        }
    }

    @Test
    void ballLost() {
        m_ScoreManager = ScoreManager.GetScoreManager();
        m_ScoreManager.SetDefault();
        m_ScoreManager.BallLost();
        assertEquals(-1000, m_ScoreManager.GetScoreTotal());
    }
}