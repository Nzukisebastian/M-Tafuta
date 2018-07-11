package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

public class Handleimage extends AppCompatActivity {

    private final String TAG=this.getClass().getName();

    ImageView ivcamera,ivgallery,ivupload,ivimage;
    GalleryPhoto galleryPhoto;
    CameraPhoto cameraPhoto;
    final int CAMERA_REQUEST=13323;
    final int GALLERY_REQUEST=22131;
    String selectedphoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handleimage);

        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());
        ivcamera=(ImageView)findViewById(R.id.ivcamera);
        ivgallery=(ImageView)findViewById(R.id.ivgallery);
        ivupload=(ImageView)findViewById(R.id.ivupload);
        ivimage=(ImageView)findViewById(R.id.ivimage);

        ivcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"something wrong while taking photos",Toast.LENGTH_LONG);
                }

            }
        });
        ivgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);

            }
        });
        ivupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedphoto==null || selectedphoto.equals("")){
                    Toast.makeText(getApplicationContext(),"choose an image",Toast.LENGTH_LONG).show();
                    return;
                }
                try {

                    Bitmap bitmap= ImageLoader.init().from(selectedphoto).requestSize(1024,1024).getBitmap();
                    String encodeimage= ImageBase64.encode(bitmap);
                    Log.d(TAG,encodeimage);
                    HashMap<String,String> postData=new HashMap<String, String>();
                    postData.put("image",encodeimage);
                    PostResponseAsyncTask task=new PostResponseAsyncTask(Handleimage.this,postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            if(s.contains("uploaded success")){
                                Toast.makeText(getApplicationContext(),"Image uploaded successfully",Toast.LENGTH_LONG).show();

                            }else if(s.contains("uploaded failed")){
                                Toast.makeText(getApplicationContext(),"Image not uploaded",Toast.LENGTH_LONG).show();
                            }else if(s==null){
                                Toast.makeText(getApplicationContext(),"Unable to connect to server",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                    task.execute("http://ictchops.me.ke/uploadimage.php");
                    task.setEachExceptionsHandler(new EachExceptionsHandler() {
                        @Override
                        public void handleIOException(IOException e) {
                            Toast.makeText(getApplicationContext(),"cannot connect to server",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void handleMalformedURLException(MalformedURLException e) {
                            Toast.makeText(getApplicationContext(),"URL Error",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void handleProtocolException(ProtocolException e) {
                            Toast.makeText(getApplicationContext(),"Protocal Error",Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                            Toast.makeText(getApplicationContext(),"Encoding Error",Toast.LENGTH_LONG).show();

                        }
                    });
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"something wrong while encoding photos",Toast.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==RESULT_OK){
            if(requestCode==CAMERA_REQUEST){
                String photoPath=cameraPhoto.getPhotoPath();
                selectedphoto=photoPath;
                Bitmap bitmap=null;
                try {
                    bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivimage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"something wrong while loading photos",Toast.LENGTH_LONG);
                }

            }
            else if(requestCode==GALLERY_REQUEST){
                Uri uri=data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath = galleryPhoto.getPath();
                selectedphoto=photoPath;
                try {
                    Bitmap bitmap= ImageLoader.init().from(photoPath).requestSize(512,512).getBitmap();
                    ivimage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),"something wrong while choosing photos",Toast.LENGTH_LONG);
                }

            }
        }
    }
}
