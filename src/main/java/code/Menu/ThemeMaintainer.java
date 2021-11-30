package code.Menu;

public class ThemeMaintainer {

    private static String m_Theme = "/CSS/Classic.css";

    public static void SetTheme(String newTheme)
    {
        m_Theme = newTheme;
    }

    public static String GetTheme()
    {
        return m_Theme;
    }
}
