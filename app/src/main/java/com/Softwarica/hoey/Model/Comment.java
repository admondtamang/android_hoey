package com.Softwarica.hoey.Model;

public class Comment {
    String _id,comment;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String _id, String comment) {
        this._id = _id;
        this.comment = comment;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
