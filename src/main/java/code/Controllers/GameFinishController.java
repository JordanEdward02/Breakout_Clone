package code.Controllers;

import code.Menu.ThemeMaintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameFinishController implements Initializable {
    private static int m_Score;
    private static String m_Message;
    @FXML
    private Label m_Title;
    @FXML
    private Label m_ScoreField;

    public GameFinishController()
    {
    }

    public GameFinishController(int Score, Stage stage, String Message) {
        m_Score=Score;
        m_Message=Message;
        try {
            String fxmlFile = "/Menu/Frames/GameFinish.fxml";
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene Scene = new Scene(m_Root);
            Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetTheme()).toExternalForm());
            stage.setScene(Scene);
            stage.setResizable(false);
            stage.setTitle("GAME FINISHED");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Submit(ActionEvent actionEvent)
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        m_Title.setText(m_Message);
        m_ScoreField.setText("SCORE: " + m_Score);
    }

}
