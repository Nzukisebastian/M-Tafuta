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
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.kosalgeek.android.photoutil.MainActivity;

public class Fragmentgallary extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  WebPagerAdater adapter;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentgallary);
        Intent intent=getIntent();
       email=intent.getExtras().getString("emailid");
        //main_view.setBackgroundColor(Color.RED);
        tabLayout=(TabLayout) findViewById(R.id.tablayout_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        adapter=new WebPagerAdater(getSupportFragmentManager());
        adapter.AddFragment(new Fragmentpic(),"Wanted Criminals");
        adapter.AddFragment(new Fragmentreport(),"Items Reports");
        adapter.AddFragment(new Fragmentppl(),"Missing Persons");
        adapter.AddFragment(new Fragmenttrace(),"Unidentified Persons");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar=getSupportActionBar();
        actionBar.  setElevation(0);

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
            case R.id.menu_red:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Report.class));
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
            case R.id.menu_pple:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                Intent i = new Intent(this, Handleimage.class);
              i.putExtra("emailid",email);
              startActivity(i);
                // main_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Msg.class));
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
                Intent pple = new Intent(this, Handleimage.class);
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
