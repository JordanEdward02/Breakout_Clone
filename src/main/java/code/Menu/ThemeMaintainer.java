package code.Menu;

import javafx.scene.image.Image;

/**
 * Singleton maintainer of themes and textures across the whole game
 * @author Jordan Lovett
 */
public class ThemeMaintainer {

    private static ThemeMaintainer m_ThemeMaintainer;
    private String m_Theme = "/CSS/Classic.css";
    private Image m_Textures;

    /**
     * Gets the singleton object
     * @return ThemeMaintainer representing the single instance of the ThemeMaintainer class
     */
    public static ThemeMaintainer GetThemeMaintainer()
    {
        if (m_ThemeMaintainer==null)
            m_ThemeMaintainer = new ThemeMaintainer();
        return m_ThemeMaintainer;
    }

    /**
     * Sets the Theme of the whole game
     * @param newTheme String representing the new theme to be set
     */
    public void SetTheme(String newTheme)
    {
        m_Theme = newTheme;
    }

    /**
     * Gets the current theme stylesheet
     * @return String reference to the CSS file for the current theme
     */
    public String GetTheme()
    {
        return m_Theme;
    }

    /**
     * Sets the texture image to pull all the game graphics from
     * @param newTexture Image texture map for the game graphics
     */
    public void SetTexture(Image newTexture)
    {
        m_Textures = newTexture;
    }

    /**
     * Returns the texture map for the current theme
     * @return Image texture map for the game graphics
     */
    public Image GetTexture()
    {
        return m_Textures;
    }
}
