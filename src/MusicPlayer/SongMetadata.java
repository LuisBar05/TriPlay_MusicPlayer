package MusicPlayer;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class SongMetadata implements Serializable {

    private int idSong;
    private String fileName, songTitle, albumName, artistName;
    private BufferedImage cover;

    public void setidSong(int idSong) {
        this.idSong = idSong;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

}
