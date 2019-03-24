package com.example.sebastian.lostfoundapp;

/**
 * Created by Nzuki on 10/6/2018.
 */
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class DataResponset{
    @SerializedName("status")
    private String status;
    @SerializedName("images")
    List<Imagest>Images;
    public List<com.example.sebastian.lostfoundapp.Imagest> getImages() {
        return Images;
    }

    public String getStatus() {
        return status;
    }
}
class Imagest{
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

