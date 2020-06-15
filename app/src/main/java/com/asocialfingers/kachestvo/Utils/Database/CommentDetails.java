package com.asocialfingers.kachestvo.Utils.Database;

import java.io.Serializable;

public class CommentDetails implements Serializable {

    long id;
    String nameSurname, comment, created_at;

    public CommentDetails() {
    }

    public CommentDetails(long id, String nameSurname, String comment, String created_at) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.comment = comment;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
