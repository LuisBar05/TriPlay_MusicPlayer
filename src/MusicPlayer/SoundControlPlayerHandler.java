package MusicPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class SoundControlPlayerHandler extends Application implements ActionListener {

    //private final String defaultPath = "src//tunes//";
    private boolean isPlaying = false;
    private Media media;
    private MediaPlayer mediaPlayer = null;
    private JToggleButton playButton;
    private JList notSelected;

    public SoundControlPlayerHandler(JToggleButton playButton) {
        this.playButton = playButton;
        this.playButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!isPlaying) {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
            playButton.setSelected(true);
            playButton.setSelectedIcon(new ImageIcon("src//icons/pause.png"));
            isPlaying = true;
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
            playButton.setSelected(false);
            playButton.setIcon(new ImageIcon("src//icons/play.png"));
            isPlaying = false;
        }
    }

    public void setNotSelected(JList songsList) {
        notSelected = songsList;
    }

    public JList getNotSelected() {
        return notSelected;
    }

    public JToggleButton getPlayButton() {
        return playButton;
    }

    public void setMediaControls(String songPath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        try {
            media = new Media(new File(songPath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
        } catch (MediaException me) {
            System.out.println("No se pudo encontrar el archivo");
            JOptionPane.showMessageDialog(null, "File could not be found", "Ooops!", JOptionPane.WARNING_MESSAGE);
        }

    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
