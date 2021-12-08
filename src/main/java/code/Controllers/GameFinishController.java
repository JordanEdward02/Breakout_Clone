package code.Controllers;

import code.Menu.ThemeMaintainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller that manages IO for dialog box when the game finishes
 * @author Jordan Lovett
 */
public class GameFinishController implements Initializable {
    private final String HIGHSCORES_PATH = "src/main/java/code/HighScores.txt";
    private static int m_Score;
    private static String m_Message;
    private static GameFinishController m_GameFinishController;

    @FXML
    private Label m_Title;
    @FXML
    private Label m_ScoreField;
    @FXML
    private TextField m_NameInput;
    @FXML
    private Button m_SubmitButton;

    /**
     * Get class to return the singleton value
     * @return The single instance of the game finish controller
     */
    public static GameFinishController GetGameFinishController()
    {
        if (m_GameFinishController == null)
            m_GameFinishController = new GameFinishController();
        return m_GameFinishController;
    }

    /**
     * Sets the data needed within the game finish dialog box
     * @param Score The score of the player as an int
     * @param Message A String displaying the cause of the game ending
     */
    public void SetData(int Score, String Message)
    {
        m_Score=Score;
        m_Message=Message;
    }

    /**
     * Loads the dialog box
     * @param stage The stage to display the game finish contents on
     */
    public void load(Stage stage) {
        try {
            String fxmlFile = "/Menu/Frames/GameFinish.fxml";
            Parent m_Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Scene Scene = new Scene(m_Root);
            Scene.getStylesheets().add(getClass().getResource(ThemeMaintainer.GetThemeMaintainer().GetTheme()).toExternalForm());
            stage.setScene(Scene);
            stage.setResizable(false);
            stage.setTitle("GAME FINISHED");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Submits the name input from the text box and the score to be stored permanently in the highscores file
     */
    public void Submit()
    {
        int i=0;
        try {
            String name = m_NameInput.getCharacters().toString();
            File scoresFile = new File(HIGHSCORES_PATH);
            Scanner myScanner = new Scanner(scoresFile);
            String[] HighScoreContents = new String[5];
            while (myScanner.hasNextLine()){
                HighScoreContents[i++] = myScanner.nextLine();
            }
            RewriteHighscores(HighScoreContents,name);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Stage stage = (Stage) m_SubmitButton.getScene().getWindow();
        stage.close();
    }

    private void RewriteHighscores(String[] HighScoresContents, String name)
    {
        try {
            int count=0;
            for (String item : HighScoresContents){
                String[] contents = item.split("\\s+");
                if (m_Score > Integer.parseInt(contents[1])){
                    // This count has to be count++ otherwise the swapping algorithm will swap the
                    // new value with the old, making them in the wrong order
                    HighScoresContents[count++] = name + " " + m_Score;
                    String temp = item;
                    while(count<5){
                        String nextLine = HighScoresContents[count];
                        HighScoresContents[count++] = temp;
                        temp = nextLine;
                    }
                    FileWriter myWriter = new FileWriter(HIGHSCORES_PATH);
                    for (String line : HighScoresContents){
                        myWriter.write(line + System.lineSeparator());
                    }
                    myWriter.close();
                    break;
                }
                count++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Called before the scene is drawn to set the display data correctly
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        m_Title.setText(m_Message);
        m_ScoreField.setText("SCORE: " + m_Score);
    }

}
