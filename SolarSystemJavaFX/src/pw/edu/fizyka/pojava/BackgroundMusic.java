//Anna Giszczak
package pw.edu.fizyka.pojava;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {
	private static final String path = "/pw/edu/fizyka/pojava/resources/music.mp3"; 
	private Media sound;
	MediaPlayer mediaPlayer;

	public BackgroundMusic() {
		sound = new Media(getClass().getResource(path).toExternalForm());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		MenuPane.buttonSoundOFF.setOnAction(event -> {
            mediaPlayer.pause();
        });
		MenuPane.buttonSoundON.setOnAction(event -> {
        	mediaPlayer.play();
        });
	}

}
