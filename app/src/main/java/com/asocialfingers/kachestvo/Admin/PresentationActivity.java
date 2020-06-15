package com.asocialfingers.kachestvo.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Connection.AppController;
import com.asocialfingers.kachestvo.Utils.Connection.ConnectionLinks;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PresentationActivity extends AppCompatActivity {

    private static String TAG = PresentationActivity.class.getSimpleName();
    private ImageButton _next, _previous;
    private Button _logout, _comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        utils();
        buttonEvents();
        imageButtonEvents();
    }

    private void utils() {
        _next = (ImageButton) findViewById(R.id.imgNext);
        _previous = (ImageButton) findViewById(R.id.imgPrevious);

        _comments = (Button) findViewById(R.id.btnComments);
        _logout = (Button) findViewById(R.id.btnLogout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(PresentationActivity.this, AdministrationActivity.class);
        startActivity(i);
        finish();
    }

    //region Database
    private void sendFeedToNext() {
        StringRequest strReq = new StringRequest(Request.Method.POST, ConnectionLinks.nextSlide, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                    } else {
                        Toast.makeText(PresentationActivity.this, "Hata oluştu! Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(PresentationActivity.this, "Server'da problem oluştu. Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Next", "1");

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq);
    }

    private void sendFeedToPrevious() {
        StringRequest strReq = new StringRequest(Request.Method.POST, ConnectionLinks.previousSlide, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                    } else {
                        Toast.makeText(PresentationActivity.this, "Hata oluştu! Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(PresentationActivity.this, "Server'da problem oluştu. Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Previous", "1");

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq);
    }
    //endregion

    //region Events
    private void buttonEvents() {
        _comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PresentationActivity.this, AdministrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PresentationActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void imageButtonEvents() {
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedToNext();
            }
        });

        _previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedToPrevious();
            }
        });
    }
    //endregion
}