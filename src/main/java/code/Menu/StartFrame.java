package code.Menu;

import code.GameplayElements.ThemeMaintainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.*;
import java.io.File;
import java.util.Objects;

/**
 * Start game class to run the application and make the main stage to draw on
 * @author Jordan Lovett
 */
public class StartFrame extends Application {

    private static ThemeMaintainer m_ThemeMaintainer;
    private final String DEF_TITLE = "Breakout Clone     space = start/pause   <-/-> = move left/right   esc = menu";

    /**
     * Main class to start the game
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Default constructor used when fxml is loaded
     */
    public StartFrame (){
        m_ThemeMaintainer = ThemeMaintainer.GetThemeMaintainer();
    }

    /**
     * Runs at the start of the game as part of the launch function
     * @param stage Stage to draw the game on
     * @throws Exception If error loading application
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Menu/StartFrame.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(m_ThemeMaintainer.GetTheme()).toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(DEF_TITLE);
        stage.show();
        m_ThemeMaintainer.SetTexture(new Image(new File(
                "src/main/resources/Assets/ClassicTextures.png").toURI().toString()));
    }
}
