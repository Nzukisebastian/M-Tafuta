package com.example.sebastian.lostfoundapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nzuki on 8/28/2018.
 */
public class Fragmentreport extends Fragment {
    public static TextView data;
    View v;
    public Fragmentreport() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.report_fragment, container, false);
        data = (TextView) v.findViewById(R.id.fetchdata);
        request();
        return v;

       }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

        }
        private void request(){
            Fetchdata fetch=new Fetchdata();
            fetch.execute();
        }
}
