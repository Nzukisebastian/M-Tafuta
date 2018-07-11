package com.example.sebastian.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main4Activity extends BaseActivity{

    Button click;
    public static TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //click=(Button)findViewById(R.id.button);
        request();
        data=(TextView)findViewById(R.id.fetchdata);
        //click.setOnClickListener(new View.OnClickListener()
        }


@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        RelativeLayout main_view=(RelativeLayout)findViewById(R.id.main_view);
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
            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Msg.class));
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;
            case R.id.menu_post:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Handleimage.class));
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //@Override
            private void request(){
                Fetchdata fetch=new Fetchdata();
                fetch.execute();
            }
    public void image(View view){

        startActivity(new Intent(this, Gallary.class));
    }
    public void info(View view){

        startActivity(new Intent(this, Main4Activity.class));
    }
}
