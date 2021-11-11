package code.Menu;

import code.GameplayElements.ElementsManager;
import code.GameplayElements.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanel extends JPanel {
    // Got to update the debug panel, as when it has many errors.
    // Skip level can overflow which breaks the game and throws an error
    // Skip level also doesn't reset the brick count as it should so makes the wall wrong and doesn't always end
    private static final Color DEF_BKG = Color.WHITE;


    private JButton m_skipLevel;
    private JButton m_resetBalls;

    private JSlider m_ballXSpeed;
    private JSlider m_ballYSpeed;

    private ElementsManager m_gameManager;

    public void SetValues(int x,int y){
        m_ballXSpeed.setValue(x);
        m_ballYSpeed.setValue(y);
    }

    public DebugPanel(ElementsManager gameManager){

        m_gameManager = gameManager;

        initialize();

        m_skipLevel = makeButton("Skip Level",e -> m_gameManager.NextLevel());
        m_resetBalls = makeButton("Reset Balls",e -> m_gameManager.ResetBallCount());

        m_ballXSpeed = makeSlider(-4,4,e -> m_gameManager.SetBallXSpeed(m_ballXSpeed.getValue()));
        m_ballYSpeed = makeSlider(-4,4,e -> m_gameManager.SetBallYSpeed(m_ballYSpeed.getValue()));

        this.add(m_skipLevel);
        this.add(m_resetBalls);

        this.add(m_ballXSpeed);
        this.add(m_ballYSpeed);

    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

}
