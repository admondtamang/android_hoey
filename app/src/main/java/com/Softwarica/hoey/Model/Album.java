package com.Softwarica.hoey.Model;

import java.util.ArrayList;

public class Album {
    String _id;
    String albumTitle,albumCover;
    Artist artist;

    public Album(String albumTitle, String albumCover) {
        this.albumTitle = albumTitle;
        this.albumCover = albumCover;
    }

    public Album(String _id, String albumTitle, String albumCover, Artist artist) {
        this._id = _id;
        this.albumTitle = albumTitle;
        this.albumCover = albumCover;
        this.artist = artist;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
