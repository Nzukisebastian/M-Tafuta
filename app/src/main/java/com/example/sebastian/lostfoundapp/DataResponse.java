package com.example.sebastian.lostfoundapp;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SEBASTIAN on 6/29/2018.
 */
public class DataResponse{
    @SerializedName("status")
    private String status;
    @SerializedName("images")
    List<Images>Images;

    public List<com.example.sebastian.lostfoundapp.Images> getImages() {
        return Images;
    }

    public String getStatus() {
        return status;
    }
}
class Images{
    @SerializedName("token")
    private String Imageid;
    @SerializedName("image")
    private  String Imagepath;

    public String getImageid() {
        return Imageid;
    }

    public String getImagepath() {
        return Imagepath;
    }
}
