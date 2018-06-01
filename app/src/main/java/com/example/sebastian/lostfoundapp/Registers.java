package com.example.sebastian.lostfoundapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Registers extends AppCompatActivity implements View.OnClickListener {
    EditText etemail,etpassword,etphone,etfullname;
    private Button buttonSubmit;

    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registers);
        //VALIDATION OF THE FORM
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        etemail = (EditText) findViewById(R.id.etemail);
        etphone = (EditText) findViewById(R.id.etphone);
        etfullname = (EditText) findViewById(R.id.etname);
        etpassword = (EditText) findViewById(R.id.etpassword);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.etname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.etpassword, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.passworderror);
        awesomeValidation.addValidation(this, R.id.etphone, "^[0-9]{2}[0-9]{8}$", R.string.mobileerror);


        buttonSubmit.setOnClickListener(this);
    }
    private void submitForm() {


        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
        String email=etemail.getText().toString().trim().toLowerCase();
        String password=etpassword.getText().toString().trim().toLowerCase();
        String fullname=etfullname.getText().toString().trim().toLowerCase();
        String phone=etphone.getText().toString().trim().toLowerCase();
        String type="register";
        Backgroundtask backgroundtask=new Backgroundtask(this);
        backgroundtask.execute(type,email,password,phone,fullname);
        }
    }
    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            submitForm();
        }
    }
}

