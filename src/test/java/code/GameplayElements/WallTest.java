package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Balls.BallRubber;
import code.GameplayElements.Levels.LevelManager;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private static Wall m_Wall;
    @BeforeEach
    void beforeEach() {
        m_Wall = new Wall();
    }

    @Test
    void getBricks() {
    }

    @Test
    void BrickCountSetAndGet() {
        m_Wall.SetBrickCount(3428);
        assertEquals(3428, m_Wall.GetBrickCount());
        m_Wall.SetBrickCount(-2472);
        assertEquals(-2472, m_Wall.GetBrickCount());
        m_Wall.SetBrickCount(1);
        assertEquals(1, m_Wall.GetBrickCount());
        m_Wall.SetBrickCount(82394);
        assertEquals(82394, m_Wall.GetBrickCount());
    }

    @Test
    void getPowerupCounter() {
        assertNotNull(m_Wall.GetPowerupCounter());
    }

    @Test
    void reduceBrickCount() {
        for (int i=0;i<10;i++)
            m_Wall.ReduceBrickCount();
        assertEquals(-10, m_Wall.GetBrickCount());
    }

    @Test
    void incrementBrickCount() {
        for (int i=0;i<10;i++)
            m_Wall.IncrementBrickCount();
        assertEquals(10, m_Wall.GetBrickCount());
    }

    @Test
    void renderWall() {
        LevelManager newLevels = new LevelManager();
        newLevels.IncrementLevel();
        m_Wall.RenderWall(newLevels, new Canvas());
        assertNotNull(m_Wall.GetBricks());
    }

    @Test
    void isDone() {
        assertTrue(m_Wall.IsDone());
        m_Wall.IncrementBrickCount();
        assertFalse(m_Wall.IsDone());
    }
}