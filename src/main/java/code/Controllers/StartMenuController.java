package code.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
public class StartMenuController {

    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(getClass().getResource(fxmlFile));
            m_Scene = new Scene(m_Root);
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadLevels(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/LevelChoiceFrame.fxml");
    }

    public void returnToStart(ActionEvent event)
    {
        loadScene(event,"/Menu/Frames/StartFrame.fxml");
    }

    public void startLevel1(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel2(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel3(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel4(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel5(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }
    public void startLevel6(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/GameBoard.fxml");
    }

    public void loadHighscoresMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/HighscoresMenu.fxml");
    }
}
