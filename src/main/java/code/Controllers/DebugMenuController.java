package code.Controllers;

import code.GameplayElements.ElementsManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DebugMenuController implements Initializable {

    private ElementsManager m_GameManager;
    @FXML
    public Slider m_XSlider;
    @FXML
    public Slider m_YSlider;

    public DebugMenuController()
    {

    }

    public DebugMenuController(Stage stage)
    {
        try
        {
            String fxmlFile = "/Menu/Frames/DebugMenu.fxml";
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene Scene = new Scene(m_Root);
            stage.setScene(Scene);
            stage.setResizable(false);
            stage.setTitle("Debug Menu");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            //Stage.setOnCloseRequest(newEvent->unlockMain(newEvent));
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void unlockMain(WindowEvent newEvent) {
    }

    public void LevelSkipButton()
    {
        /*
        if (m_GameManager.NewLevel())
        {
            m_GameManager.LevelSkip();
        }

         */
    }

    public void ResetBalls()
    {
        //m_GameManager.ResetBallCount();
    }

    public void SetXSpeed()
    {
        /*
        Ball b = m_GameManager.GetBall();
        b.SetXSpeed((int) m_XSlider.getValue());

         */
    }

    public void SetYSpeed()
    {/*
        Ball b = m_GameManager.GetBall();
        b.SetYSpeed((int) m_YSlider.getValue());
        */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        /*
        Ball b = m_GameManager.GetBall();
        m_XSlider.adjustValue(b.GetSpeedX());
        m_YSlider.adjustValue(b.GetSpeedY());

         */
    }
}
