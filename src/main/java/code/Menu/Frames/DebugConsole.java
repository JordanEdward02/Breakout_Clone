package code.Menu.Frames;

import code.Controllers.DebugMenuController;
import code.GameplayElements.ElementsManager;
import code.Menu.Painters.DebugPanelPainter;

import javax.swing.*;
import java.awt.*;

public class DebugConsole extends JDialog{

    private static final String TITLE = "Debug Console";

    private JFrame m_owner;
    private DebugMenuController m_DebugMenuController;


    public void setLocation(){
        int x = ((m_owner.getWidth() - this.getWidth()) / 2) + m_owner.getX();
        int y = ((m_owner.getHeight() - this.getHeight()) / 2) + m_owner.getY();
        this.setLocation(x,y);
    }

    public DebugConsole(JFrame owner,ElementsManager gameManager,GameBoard gameBoard){

        ElementsManager m_gameManager = gameManager;
        m_owner = owner;
        initialize();

        DebugPanelPainter m_debugPanelPainter = new DebugPanelPainter(m_gameManager);
        this.add(m_debugPanelPainter,BorderLayout.CENTER);
        m_DebugMenuController = new DebugMenuController(gameBoard, this, gameManager, m_debugPanelPainter);
        m_debugPanelPainter.SetController(m_DebugMenuController);
        this.addWindowListener(m_DebugMenuController);
        this.pack();
    }

    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
    }


}
