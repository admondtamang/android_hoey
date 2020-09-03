package com.Softwarica.hoey.Model;

import java.util.ArrayList;
import java.util.List;

public class Song {
    String title, link,_id,played;
    int duration, year;
    Album album;

    public Song(String title, String link, int duration, int year, Album album) {
        this.title = title;
        this.link = link;
        this.duration = duration;
        this.year = year;
        this.album = album;
    }

    public Song(String title, String link, int duration, int year) {
        this.title = title;
        this.link = link;
        this.duration = duration;
        this.year = year;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }
}
