package MusicPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JToggleButton;

public class SoundControlPlayerHandler extends Application implements ActionListener {

    private final String defaultPath = "src//tunes//";
    private boolean isPlaying = false;
    private Media media;
    private MediaPlayer mediaPlayer = null;
    private JToggleButton playButton;

    public SoundControlPlayerHandler(JToggleButton playButton) {
        this.playButton = playButton;
        this.playButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!isPlaying) {
            mediaPlayer.play();
            isPlaying = true;
        } else {
            mediaPlayer.pause();
            isPlaying = false;
        }
    }

    public JToggleButton getPlayButton() {
        return playButton;
    }

    public void setMediaControls(String songName) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        media = new Media(new File(defaultPath.concat(songName)).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
