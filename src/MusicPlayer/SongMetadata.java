package MusicPlayer;

import java.io.Serializable;

public class SongMetadata implements Serializable {

    private String fileURL, fileName, songTitle, albumName, artistName;

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getFileURL() {
        return fileURL;
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
