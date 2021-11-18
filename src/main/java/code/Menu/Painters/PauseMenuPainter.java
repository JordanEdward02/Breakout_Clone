package code.Menu.Painters;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class PauseMenuPainter {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";

    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);

    private Rectangle m_continueButtonRect;
    private Rectangle m_exitButtonRect;
    private Rectangle m_restartButtonRect;

    private Font m_MenuFont;
    private int m_MenuInt=0;

    public Rectangle GetContinueBut()
    {
        return m_continueButtonRect;
    }

    public Rectangle GetExitBut()
    {
        return m_exitButtonRect;
    }

    public Rectangle GetRestartBut()
    {
        return m_restartButtonRect;
    }

    public PauseMenuPainter()
    {
        m_MenuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
    }

    public void DrawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();
        final float fAlphaFloat=0.55f;

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,fAlphaFloat);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();
        g2d.setFont(m_MenuFont);
        g2d.setColor(MENU_COLOR);
        final int iTWO=2, iTEN=10, iEIGHT=8, iFOUR=4;
        final double dTHREE=3.0;

        if(m_MenuInt == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            m_MenuInt = m_MenuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (DEF_WIDTH - m_MenuInt) / iTWO;
        int y = DEF_HEIGHT / iTEN;

        g2d.drawString(PAUSE,x,y);

        x = DEF_WIDTH / iEIGHT;
        y = DEF_HEIGHT / iFOUR;


        if(m_continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            m_continueButtonRect = m_MenuFont.getStringBounds(CONTINUE,frc).getBounds();
            m_continueButtonRect.setLocation(x,y-m_continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= iTWO;

        if(m_restartButtonRect == null){
            m_restartButtonRect = (Rectangle) m_continueButtonRect.clone();
            m_restartButtonRect.setLocation(x,y-m_restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= dTHREE/iTWO;

        if(m_exitButtonRect == null){
            m_exitButtonRect = (Rectangle) m_continueButtonRect.clone();
            m_exitButtonRect.setLocation(x,y-m_exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }


}
