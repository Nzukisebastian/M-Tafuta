package com.example.sebastian.lostfoundapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Adminfragment extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  WebPagerAdater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfragment);

        tabLayout=(TabLayout) findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        adapter=new WebPagerAdater(getSupportFragmentManager());
        adapter.AddFragment(new FragmentadminCriminal(),"wanted criminals");
        adapter.AddFragment(new FragmentadminFoundItems(),"found items");
        adapter.AddFragment(new FragmentAdminMissingPerson(),"missing persons");
        adapter.AddFragment(new FragmentAdminUnidentifiedPerson(),"unidentified persons");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setElevation(0);
    }
}
