package code.Menu.Painters;

import code.GameplayElements.ElementsManager;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanelPainter extends JPanel {
    // Got to update the debug panel, as when it has many errors.
    // Skip level can overflow which breaks the game and throws an error
    // Skip level also doesn't reset the brick count as it should so makes the wall wrong and doesn't always end
    private static final Color DEF_BKG = Color.WHITE;


    private JButton m_skipLevel;
    private JButton m_resetBalls;

    private JSlider m_ballXSpeed;
    private JSlider m_ballYSpeed;

    private ElementsManager m_gameManager;

    public void SetValues(int x,int y)
    {
        m_ballXSpeed.setValue(x);
        m_ballYSpeed.setValue(y);
    }

    public DebugPanelPainter(ElementsManager gameManager){

        m_gameManager = gameManager;

        initialize();

        m_skipLevel = makeButton("Skip Level",e -> m_gameManager.NextLevel());
        m_resetBalls = makeButton("Reset Balls",e -> m_gameManager.ResetBallCount());

        m_ballXSpeed = makeSlider(-4,4,e -> m_gameManager.SetBallXSpeed(m_ballXSpeed.getValue()));
        m_ballYSpeed = makeSlider(-4,4,e -> m_gameManager.SetBallYSpeed(m_ballYSpeed.getValue()));

        this.add(makeButtonLayout());
        this.add(makeSliderLayout());

    }

    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,1));
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
    private JPanel makeButtonLayout()
    {
        JPanel newPanel = new JPanel();
        newPanel.add(m_skipLevel);
        newPanel.add(m_resetBalls);
        newPanel.setLayout(new FlowLayout());
        return newPanel;
    }
    private JPanel makeSliderLayout()
    {
        JPanel newPanel = new JPanel();
        newPanel.add(new JLabel("X Speed", SwingConstants.CENTER));
        newPanel.add(m_ballXSpeed);
        newPanel.add(new JLabel("Y Speed", SwingConstants.CENTER));
        newPanel.add(m_ballYSpeed);
        newPanel.setLayout(new GridLayout(4,1));
        return newPanel;
    }

}
