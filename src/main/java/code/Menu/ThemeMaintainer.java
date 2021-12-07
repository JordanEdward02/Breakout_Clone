package code.Menu;

import javafx.scene.image.Image;

public class ThemeMaintainer {

    private static ThemeMaintainer m_ThemeMaintainer;
    private String m_Theme = "/CSS/Classic.css";
    private Image m_Textures;

    public static ThemeMaintainer GetThemeMaintainer()
    {
        if (m_ThemeMaintainer==null)
            m_ThemeMaintainer = new ThemeMaintainer();
        return m_ThemeMaintainer;
    }
    public void SetTheme(String newTheme)
    {
        m_Theme = newTheme;
    }

    public String GetTheme()
    {
        return m_Theme;
    }

    public void SetTexture(Image newTexture)
    {
        m_Textures = newTexture;
    }

    public Image GetTexture()
    {
        return m_Textures;
    }
}
