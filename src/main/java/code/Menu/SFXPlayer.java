package code.Menu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SFXPlayer {

    private static final String BUTTON_SFX = "/SFX/Button.wav";
    private static final String COLLISION_SFX = "/SFX/Collision.wav";

    public static void ButtonSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(BUTTON_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }

    public static void CollisionSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(COLLISION_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }
}
