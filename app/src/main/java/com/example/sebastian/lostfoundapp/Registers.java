package com.example.sebastian.lostfoundapp;

import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Patterns;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.basgeekball.awesomevalidation.AwesomeValidation;
        import com.basgeekball.awesomevalidation.ValidationStyle;

        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.HashMap;
        import java.util.Map;

public class Registers extends AppCompatActivity implements View.OnClickListener {
    EditText etemail,etpassword,etphone,etfullname;
    private Button buttonSubmit;
    private ProgressDialog progressDialog;
    //URL to RegisterDevice.php
    private static final String URL_REGISTER_DEVICE = "http://ictchops.me.ke/FcmExample/RegisterDevice.php";

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
        //adding listener to view
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

    //storing token to mysql server
    private void sendTokenToServer() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering");
        progressDialog.show();

        final String token = SharedPrefManager.getInstance(this).getDeviceToken();
        final String email=etemail.getText().toString().trim().toLowerCase();

        if (token == null) {
            progressDialog.dismiss();
            Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(Registers.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Registers.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            submitForm();
            sendTokenToServer();
        }
    }
}

