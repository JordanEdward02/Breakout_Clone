package code.Menu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SFXPlayer {

    private final String BUTTON_SFX = "/SFX/Button.wav";
    private final String COLLISION_SFX = "/SFX/Collision.wav";

    public void ButtonSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(BUTTON_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }

    public void CollisionSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(COLLISION_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }
}
