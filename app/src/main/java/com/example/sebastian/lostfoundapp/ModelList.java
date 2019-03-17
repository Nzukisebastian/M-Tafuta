package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 2/1/2018.
 */
public class ModelList{

    String id,title,katerangan,img;
    public ModelList(){

    };
    public ModelList(String id, String title, String katerangan, String img) {
        this.id = id;
        this.title = title;
        this.katerangan = katerangan;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKaterangan() {
        return katerangan;
    }

    public void setKaterangan(String katerangan) {
        this.katerangan = katerangan;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

