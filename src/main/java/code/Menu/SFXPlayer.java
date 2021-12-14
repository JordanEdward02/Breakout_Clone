package code.Menu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Sound effects player
 * @author Jordan Lovett
 */
public class SFXPlayer {

    private final String BUTTON_SFX = "/SFX/Button.wav";
    private final String COLLISION_SFX = "/SFX/Collision.wav";

    /**
     * Plays the sound effect for when a menu button is pressed
     */
    public void ButtonSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(BUTTON_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }

    /**
     * Plays the sound effect for when a ball collision occurs
     */
    public void CollisionSFX()
    {
        Media pick = new Media(SFXPlayer.class.getResource(COLLISION_SFX).toExternalForm());
        MediaPlayer player = new MediaPlayer(pick);
        player.play();
    }
}
