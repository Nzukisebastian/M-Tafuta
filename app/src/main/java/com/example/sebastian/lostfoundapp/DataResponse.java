package com.example.sebastian.lostfoundapp;

/**
 * Created by SEBASTIAN on 7/4/2018.
 */
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
    @SerializedName("id")
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
