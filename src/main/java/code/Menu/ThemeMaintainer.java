package code.Menu;

import javafx.scene.image.Image;

public class ThemeMaintainer {

    private static String m_Theme = "/CSS/Classic.css";
    private static Image m_Textures;

    public static void SetTheme(String newTheme)
    {
        m_Theme = newTheme;
    }

    public static String GetTheme()
    {
        return m_Theme;
    }

    public static void SetTexture(Image newTexture)
    {
        m_Textures = newTexture;
    }

    public static Image GetTexture()
    {
        return m_Textures;
    }
}
