package code.Controllers;

public class ThemeMaintainer {

    private static String m_Theme;

    public static void SetTheme(String newTheme)
    {
        m_Theme = newTheme;
    }

    public static String GetTheme()
    {
        return m_Theme;
    }
}
