/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public FieldGetter(File audioFile) throws CannotReadFile {
        setSongFile(audioFile);
    }

    public final void setSongFile(File songFile) throws CannotReadFile {
        try {
            AudioFile f = AudioFileIO.read(songFile);
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

}
