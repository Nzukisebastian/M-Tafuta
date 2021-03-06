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

import com.kosalgeek.android.photoutil.MainActivity;

public class Fragmentgallarys extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  WebPagerAdater adapter;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentgallarys);
        Intent intent=getIntent();
        email=intent.getExtras().getString("emailid");
        //main_view.setBackgroundColor(Color.RED);
        tabLayout=(TabLayout) findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        adapter=new WebPagerAdater(getSupportFragmentManager());
        adapter.AddFragment(new Fragmentpic(),"wanted criminals");
        adapter.AddFragment(new Fragmentitemlost(),"found items");
        adapter.AddFragment(new Fragmentppl(),"missing persons");
        adapter.AddFragment(new Fragmenttrace(),"unidentified persons");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setElevation(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        LinearLayout main_view=(LinearLayout) findViewById(R.id.main_view);
        switch(item.getItemId()){
            case R.id.menu_found:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent found = new Intent(this, Founditem.class);
                found.putExtra("emailid",email);
                startActivity(found);
                //main_view.setBackgroundColor(Color.RED);
                return true;
            case R.id.menu_green:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Phonecall.class));
                // main_view.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.menu_foundproperty:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,FounditemActivity.class));
                // main_view.setBackgroundColor(Color.GREEN);
                return true;

            case R.id.menu_pple:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                //Intent i = new Intent(this, Handleimage.class);
                Intent i = new Intent(this,Unidentified.class);
                i.putExtra("emailid",email);
                startActivity(i);
                // main_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent criminal = new Intent(this,Handleimage.class);
                criminal.putExtra("emailid",email);
                startActivity(criminal);
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;

            case R.id.menu_mpesa:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Mpesa.class));
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_post:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent pple = new Intent(this, Itemlost.class);
                pple.putExtra("emailid",email);
                startActivity(pple);
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_miss:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent miss = new Intent(this,Missing.class);
                miss.putExtra("emailid",email);
                startActivity(miss);
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_logout:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this, MainActivity.class));
                // main_view.setBackgroundColor(Color.GREEN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
