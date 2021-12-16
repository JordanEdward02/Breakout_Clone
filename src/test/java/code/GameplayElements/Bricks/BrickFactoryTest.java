package code.GameplayElements.Bricks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

    private BrickFactory m_Factory;
    @Test
    void getBrick() {
        m_Factory = new BrickFactory();
        assertNotNull(m_Factory.GetBrick(new Point(), new Dimension(), 1));
        assertNotNull(m_Factory.GetBrick(new Point(), new Dimension(), 2));
        assertNotNull(m_Factory.GetBrick(new Point(), new Dimension(), 3));
        assertNull(m_Factory.GetBrick(new Point(), new Dimension(), 0));
    }
}