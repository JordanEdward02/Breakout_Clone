package code.Controllers;

import code.Menu.SFXPlayer;
import code.Menu.ThemeMaintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HighMenuController implements Initializable {

    private static final int FOUR = 4;
    private Stage m_Stage;
    private Scene m_Scene;
    private Parent m_Root;
    @FXML
    public Label m_ScoreOne;
    @FXML
    public Label m_ScoreTwo;
    @FXML
    public Label m_ScoreThree;
    @FXML
    public Label m_ScoreFour;
    @FXML
    public Label m_ScoreFive;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            File levelFile = new File("src/main/java/code/HighScores.txt");
            Scanner myScanner = new Scanner(levelFile);
            int i;
            for (i=0;i<FOUR; i++)
            {
                switch(i) {
                    case 0:
                        m_ScoreOne.setText(myScanner.nextLine());
                    case 1:
                        m_ScoreTwo.setText(myScanner.nextLine());
                    case 2:
                        m_ScoreThree.setText(myScanner.nextLine());
                    case 3:
                        m_ScoreFour.setText(myScanner.nextLine());
                    case 4:
                        m_ScoreFive.setText(myScanner.nextLine());
                    default:
                        return;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR READING THE HIGHSCORES\n" + e);
        }
    }

    private void loadScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            SFXPlayer.GetSFXPlayer().ButtonSFX();
            m_Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            m_Scene = new Scene(m_Root);
            m_Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetThemeMaintainer().GetTheme()).toExternalForm());
            m_Stage.setScene(m_Scene);
            m_Stage.show();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void returnToStart(ActionEvent event)
    {
        loadScene(event,"/Menu/Frames/StartFrame.fxml");
    }

}
