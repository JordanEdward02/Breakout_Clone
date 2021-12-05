package code.Controllers;

import code.GameplayElements.Balls.Ball;
import code.GameplayElements.ElementsManager;
import code.Menu.Painters.GameBoardPainter;
import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DebugMenuController implements Initializable {

    private static ElementsManager m_GameManager;
    private static GameBoardPainter m_GameBoardPainter;
    @FXML
    public Slider m_XSlider;
    @FXML
    public Slider m_YSlider;

    public DebugMenuController()
    {
    }

    public DebugMenuController(Stage stage, ElementsManager gameManager, GameBoardPainter gameBoardPainter)
    {
        m_GameManager = gameManager;
        m_GameBoardPainter = gameBoardPainter;
        try
        {
            String fxmlFile = "/Menu/Frames/DebugMenu.fxml";
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene Scene = new Scene(m_Root);
            Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetTheme()).toExternalForm());
            stage.setScene(Scene);
            stage.setResizable(false);
            stage.setTitle("Debug Menu");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void LevelSkipButton()
    {
        SFXPlayer.ButtonSFX();
        if (m_GameManager.NewLevel())
        {
            m_GameManager.LevelSkip();
        }
        m_GameBoardPainter.Refresh();
    }

    public void ResetBalls()
    {
        SFXPlayer.ButtonSFX();
        m_GameManager.ResetBallCount();
        m_GameBoardPainter.Refresh();
    }

    public void SetXSpeed()
    {
        m_GameManager.SetBallXSpeed((int)m_XSlider.getValue());
    }

    public void SetYSpeed()
    {
        m_GameManager.SetBallYSpeed((int)m_YSlider.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Ball b = m_GameManager.GetBall();
        m_XSlider.adjustValue(b.GetSpeedX());
        m_YSlider.adjustValue(b.GetSpeedY());
    }
}
