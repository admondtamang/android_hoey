package com.Softwarica.hoey.Model;

public class Playlist {
    String playlistName,songName;

    public Playlist(String playlistName, String songName) {
        this.playlistName = playlistName;
        this.songName = songName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
