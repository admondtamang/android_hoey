package com.Softwarica.hoey.Model;

import java.util.ArrayList;

public class Favourite {
    String _id;
    User user;
    ArrayList<Song> song;

    public Favourite(String _id, User user, ArrayList<Song> song) {
        this._id = _id;
        this.user = user;
        this.song = song;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Song> getSong() {
        return song;
    }

    public void setSong(ArrayList<Song> song) {
        this.song = song;
    }
}
