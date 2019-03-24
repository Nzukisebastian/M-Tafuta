package com.example.sebastian.lostfoundapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nzuki on 9/20/2018.
 */
public class DataResponsep {

    @SerializedName("status")
    private String status;
    @SerializedName("images")
    List<Imagesp> Images;

    public List<com.example.sebastian.lostfoundapp.Imagesp> getImages() {
        return Images;
    }

    public String getStatus() {
        return status;
    }
}
class Imagesp{
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
