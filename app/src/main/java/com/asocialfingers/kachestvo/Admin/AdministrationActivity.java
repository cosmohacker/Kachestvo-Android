package com.asocialfingers.kachestvo.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Adapter.AllCommentsAdapter;
import com.asocialfingers.kachestvo.Utils.Connection.AppController;
import com.asocialfingers.kachestvo.Utils.Connection.ConnectionLinks;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;
import com.asocialfingers.kachestvo.Utils.Divider.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdministrationActivity extends AppCompatActivity {

    private static String TAG = AdministrationActivity.class.getSimpleName();
    private ArrayList<CommentDetails> commentDetails;
    private Button _logout, _presentation;
    private AllCommentsAdapter mAdapter;
    private RecyclerView _recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        utils();
        recyclerViewEvents();
        buttonEvents();
    }

    private void utils() {
        _presentation = (Button) findViewById(R.id.btnPresentation);
        _logout = (Button) findViewById(R.id.btnLogout);
    }

    private void buttonEvents() {
        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        _presentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdministrationActivity.this, PresentationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void recyclerViewEvents() {

        _recycler = (RecyclerView) findViewById(R.id.commentsRecycler);
        commentDetails = new ArrayList<>();
        mAdapter = new AllCommentsAdapter(getApplicationContext(), commentDetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        _recycler.setHasFixedSize(true);
        _recycler.setLayoutManager(layoutManager);
        _recycler.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        _recycler.setItemAnimator(new DefaultItemAnimator());
        _recycler.setAdapter(mAdapter);

        fetchChatRooms();
    }

    private void fetchChatRooms() {
        StringRequest strReq = new StringRequest(Request.Method.GET, ConnectionLinks.getComment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    // check for error flag
                    if (obj.getBoolean("error") == false) {
                        JSONArray chatRoomsArray = obj.getJSONArray("comments");
                        for (int i = 0; i < chatRoomsArray.length(); i++) {
                            JSONObject commentOBJ = (JSONObject) chatRoomsArray.get(i);
                            CommentDetails cm = new CommentDetails();
                            cm.setId(commentOBJ.getLong("Id"));
                            cm.setNameSurname(commentOBJ.getString("Name"));
                            cm.setComment(commentOBJ.getString("Comment"));
                            cm.setCreated_at(commentOBJ.getString("Created_At"));

                            commentDetails.add(cm);
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(AdministrationActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}