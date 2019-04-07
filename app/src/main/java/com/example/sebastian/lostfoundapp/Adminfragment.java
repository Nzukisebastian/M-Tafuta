package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabs,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        LinearLayout main_admin=(LinearLayout) findViewById(R.id.main_admin);
        switch(item.getItemId()) {
            case R.id.menu_found:
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent found = new Intent(this, Founditemadmins.class);
                startActivity(found);
                //main_view.setBackgroundColor(Color.RED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
