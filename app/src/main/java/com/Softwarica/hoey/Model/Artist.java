package com.Softwarica.hoey.Model;

public class Artist {
    String artistName,artistImage;

    public Artist(String artistName, String artistImage) {
        this.artistName = artistName;
        this.artistImage = artistImage;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }
}
