package com.example.sebastian.lostfoundapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nzuki on 8/28/2018.
 */
public class WebPagerAdater extends FragmentPagerAdapter {

    private final List<Fragment>lstfragment=new ArrayList<>();
    private final List<String>lstTitles= new ArrayList<>();

    public WebPagerAdater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lstfragment.get(position);
    }

    @Override
    public int getCount() {
        return lstTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitles.get(position);
    }
    public void AddFragment(Fragment fragment,String title){
        lstfragment.add(fragment);
        lstTitles.add(title);
    }
}
