package com.example.sebastian.lostfoundapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Report extends AppCompatActivity implements View.OnClickListener{

    EditText report;
    private Button buttonSubmit;
    private ProgressDialog progressDialog;
    //URL
    private static final String URL_REGISTER_DEVICE = "http://ictchops.me.ke/FcmExample/sendMultiplePush.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        report=(EditText)findViewById(R.id.report);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(this);

    }

    //storing token to mysql server
    private void sendTokenToServers() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending report...");
        progressDialog.show();

        final String sent=report.getText().toString().trim().toLowerCase();
        final String titles="M-Tafuta";
        final String messages="Hello customer,someone has lost an item,kindly view full details in the system for help!";
        final String emails="mutiso@gmail.com";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(Report.this, obj.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Report.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               // params.put("report",sent);
                params.put("message",messages);
                params.put("title",titles);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSubmit) {
            sendTokenToServers();
        }
    }
}
