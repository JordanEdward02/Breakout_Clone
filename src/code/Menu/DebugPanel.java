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


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private ElementsManager m_gameManager;

    public DebugPanel(ElementsManager gameManager){

        m_gameManager = gameManager;

        initialize();

        skipLevel = makeButton("Skip Level",e -> m_gameManager.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> m_gameManager.resetBallCount());

        ballXSpeed = makeSlider(-4,4,e -> m_gameManager.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> m_gameManager.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    public void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    public JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    public JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
