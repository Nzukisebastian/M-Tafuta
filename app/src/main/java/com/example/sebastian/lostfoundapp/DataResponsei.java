package com.example.sebastian.lostfoundapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponsei {

    @SerializedName("status")
    private String status;
    @SerializedName("images")
    List<Imagesi> Images;

    public List<com.example.sebastian.lostfoundapp.Imagesi> getImages() {
        return Images;
    }

    public String getStatus() {
        return status;
    }
}
class Imagesi{
    @SerializedName("token")
    private String Imageid;
    @SerializedName("image")
    private  String Imagepath;
    @SerializedName("details")
    private  String details;

    public String getDetails() {
        return details;
    }

    public String getImageid() {
        return Imageid;
    }

    public String getImagepath() {
        return Imagepath;
    }
}
