package code.Menu.Frames;

import javafx.scene.layout.BorderPane;

public class GameFrame extends BorderPane{

    private GameBoard m_GameBoard;

    public GameFrame(){
        super();
        m_GameBoard = new GameBoard();
        this.getChildren().add(m_GameBoard);
        this.setVisible(true);
    }

    public GameFrame(int startLevel)
    {
        this();
//        m_GameBoard.startLevel(startLevel);
    }

}
