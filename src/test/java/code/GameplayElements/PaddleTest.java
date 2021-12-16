package code.GameplayElements;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.Balls.BallRubber;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PaddleTest {
    Paddle m_Paddle;
    @BeforeEach
    void BeforeEach(){
        m_Paddle = new Paddle(new Point(100,100), new Canvas(600,600));
    }

    @Test
    void getLocation() {
        assertNotNull(m_Paddle.getLocation());
    }

    @Test
    void getSourceX() {
        assertEquals(0.0, m_Paddle.GetSourceX());
    }

    @Test
    void getSourceY() {
        assertEquals(60.0, m_Paddle.GetSourceY());
    }

    @Test
    void getHeight() {
        assertEquals(10,m_Paddle.GetHeight());
    }

    @Test
    void getWidth() {
        assertEquals(150, m_Paddle.GetWidth());
    }

    @Test
    void moveTests() {
        m_Paddle.MoveLeft();
        m_Paddle.Move();
        assertEquals(5, m_Paddle.getLocation().getX());
        m_Paddle.MoveRight();
        m_Paddle.Move();
        m_Paddle.Move();
        assertEquals(15, m_Paddle.getLocation().getX());
        m_Paddle.Stop();
        m_Paddle.Move();
        assertEquals(15, m_Paddle.getLocation().getX());
    }

    @Test
    void moveTo() {
        m_Paddle.MoveTo(new Point(50,85));
        assertEquals(new Point(50,85), m_Paddle.getLocation());
    }
}