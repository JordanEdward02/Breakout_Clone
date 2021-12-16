package code.GameplayElements;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeMaintainerTest {
    private static ThemeMaintainer m_ThemeMaintainer;
    @BeforeAll
    static void BeforeAll(){
        m_ThemeMaintainer = ThemeMaintainer.GetThemeMaintainer();
    }
    @Test
    void getThemeMaintainer() {
        assertNotNull(ThemeMaintainer.GetThemeMaintainer());
    }

    @Test
    void ThemeChangingTests() {
        m_ThemeMaintainer.SetTheme("Test String 1");
        assertEquals("Test String 1", m_ThemeMaintainer.GetTheme());
        m_ThemeMaintainer.SetTheme("Test 2");
        assertEquals("Test 2", m_ThemeMaintainer.GetTheme());
    }
}