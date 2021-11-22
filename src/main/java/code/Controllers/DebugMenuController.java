package code.Controllers;

import code.GameplayElements.Ball;
import code.GameplayElements.ElementsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import java.net.URL;
import java.util.ResourceBundle;

public class DebugMenuController implements Initializable {

    private ElementsManager m_GameManager;
    @FXML
    public Slider m_XSlider;
    @FXML
    public Slider m_YSlider;

    public DebugMenuController(ElementsManager gameManager)
    {
        m_GameManager = gameManager;
    }

    public void LevelSkipButton()
    {
        if (m_GameManager.NewLevel())
        {
            m_GameManager.LevelSkip();
        }
    }

    public void ResetBalls()
    {
        m_GameManager.ResetBallCount();
    }

    public void SetXSpeed()
    {
        Ball b = m_GameManager.GetBall();
        b.SetXSpeed((int) m_XSlider.getValue());
    }

    public void SetYSpeed()
    {
        Ball b = m_GameManager.GetBall();
        b.SetYSpeed((int) m_YSlider.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Ball b = m_GameManager.GetBall();
        m_XSlider.adjustValue(b.GetSpeedX());
        m_YSlider.adjustValue(b.GetSpeedY());
    }
}
