package com.Softwarica.hoey.Model;

import java.util.List;
import java.util.List;

public class User {
    String _id,username,email,password,image;
    Boolean admin;
    List<Song> songs;

    public User(String _id, String username, String email, String password, String image, List<Song> songs) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.image = image;
        this.songs = songs;
    }

    public User(String username, String email, String image, Boolean admin) {
        this.username = username;
        this.email = email;
        this.image = image;
        this.admin = admin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return image;
    }

    public void setPicture(String image) {
        this.image = image;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
