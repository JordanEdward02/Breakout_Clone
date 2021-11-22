package code.Menu.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    public static final String DEF_TITLE = "Breakout Clone     space = start/pause   ←/→ = move left/right   esc = menu";
    private GameBoard m_GameBoard;
    private boolean m_Gaming;

    public GameFrame(){
        super();
        m_Gaming = false;
        this.setLayout(new BorderLayout());
        m_GameBoard = new GameBoard(this);
        this.add(m_GameBoard,BorderLayout.CENTER);
        initialize();
        this.addWindowFocusListener(this);
    }
    public GameFrame(int startLevel)
    {
        this();
        m_GameBoard.startLevel(startLevel);
    }
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        // the first time the frame loses focus is because it has been disposed to install the GameBoard, so went it regains the focus it's ready to play. of course calling a method such as 'onLostFocus' is useful only if the GameBoard as been displayed at least once
        m_Gaming = true;
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(m_Gaming)
            m_GameBoard.OnLostFocus();

    }
}
