package code.GameplayElements.Levels;

import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelManagerTest {
    private LevelManager m_LevelManager;
    @BeforeEach
    void BeforeEach(){
        LevelManager.setStartLevel(1);
        m_LevelManager = new LevelManager();
    }

    @Test
    void hasNextLevel() {
        assertTrue(m_LevelManager.HasNextLevel());
        LevelManager.setStartLevel(324);
        m_LevelManager = new LevelManager();
        assertFalse(m_LevelManager.HasNextLevel());
    }

    @Test
    void incrementLevel() {
        try{
            m_LevelManager = new LevelManager();
            while (m_LevelManager.HasNextLevel()){
                m_LevelManager.IncrementLevel();
            }
        }
        catch (Exception e){
            fail(e);
        }
    }

    @Test
    void setStartLevel() {
        int i=1;
        boolean testing = true;
        try{
            while(testing) {
                LevelManager.setStartLevel(i++);
                m_LevelManager = new LevelManager();
                if (m_LevelManager.HasNextLevel() && i >6){
                    fail("Found a next level when i > 6");
                }
                if (i>10){
                    testing = false;
                }
            }
        }
        catch (Exception e){
            fail(e);
        }
    }

    @Test
    void renderWall() {
        assertNotNull(m_LevelManager.RenderWall(new Canvas()));
    }
}