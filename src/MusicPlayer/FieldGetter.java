package MusicPlayer;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.images.Artwork;

/**
 *
 * @author adrian
 */
public class FieldGetter {

    private Tag tags;
    /** 
     * @author Renata (@BalbyReny)
     */
    private int duration_milis; //variable to store the duration of the song as it were another tag (for the jProgressBar thing)
    
    public FieldGetter(File audioFile) throws CannotReadFile {
        setSongFile(audioFile);
    }

    public final void setSongFile(File songFile) throws CannotReadFile {
        try {
            AudioFile f = AudioFileIO.read(songFile);
            /** 
             * @author Renata (@BalbyReny)
             */
            duration_milis = f.getAudioHeader().getTrackLength(); //Problems with this, it doesn't really gets the track length in miliseconds and is suposed to ???
            tags = f.getTag();
        } catch (IOException | CannotReadException | InvalidAudioFrameException | ReadOnlyFileException | KeyNotFoundException | TagException ex) {
            throw new CannotReadFile(ex.getLocalizedMessage());
        }
    }

    public String getTitle() {
        return tags.getFirst(FieldKey.TITLE);
    }

    public String getArtist() {
        return tags.getFirst(FieldKey.ARTIST);
    }

    public String getAlbum() {
        return tags.getFirst(FieldKey.ALBUM);
    }

    public Artwork getArtwork() {
        return tags.getFirstArtwork();
    }
    
    public int getDuration() {
        return duration_milis; //feeling sad about this method, it really doesn't return anything, I don't want it to be useless ??
    }

}
