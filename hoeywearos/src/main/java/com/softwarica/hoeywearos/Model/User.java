package com.softwarica.hoeywearos.Model;

public class User {
    String _id,username,email,password,picture;

    public User(String _id, String username, String email, String password, String picture) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
    }

    public User(String username, String email, String password, String picture) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
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
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
