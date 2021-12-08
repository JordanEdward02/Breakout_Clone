package code;

import code.Menu.ThemeMaintainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.File;
import java.util.Objects;

public class StartFrame extends Application {

    private static ThemeMaintainer m_ThemeMaintainer;
    private final String DEF_TITLE = "Breakout Clone     space = start/pause   <-/-> = move left/right   esc = menu";

    public static void main(String[] args)
    {
        launch(args);
    }
    
    public StartFrame (){
        m_ThemeMaintainer = ThemeMaintainer.GetThemeMaintainer();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Menu/Frames/StartFrame.fxml")));
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
