package code.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.ActionEvent;
import java.util.Objects;
import javafx.scene.Scene;
import code.Menu.Frames.GameFrame;

import javax.swing.*;

public class StartMenuController {

    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
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

    private void startLevelDefault(ActionEvent event)
    {
        m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        m_Stage.hide();
    }

    public void startLevel1(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame());
    }
    public void startLevel2(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame(2));
    }
    public void startLevel3(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame(3));
    }
    public void startLevel4(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame(4));
    }
    public void startLevel5(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame(5));
    }
    public void startLevel6(ActionEvent event)
    {
        startLevelDefault(event);
        SwingUtilities.invokeLater(()-> new GameFrame(6));
    }

    public void loadHighscoresMenu(ActionEvent event)
    {
        loadScene(event, "/Menu/Frames/HighscoresMenu.fxml");
    }
}
