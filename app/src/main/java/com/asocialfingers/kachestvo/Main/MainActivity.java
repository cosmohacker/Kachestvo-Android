package com.asocialfingers.kachestvo.Main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.asocialfingers.kachestvo.Admin.AdministrationActivity;
import com.asocialfingers.kachestvo.Admin.LoginActivity;
import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Tabs.About.AboutFragment;
import com.asocialfingers.kachestvo.Tabs.Comments.CommentsFragment;
import com.asocialfingers.kachestvo.Tabs.Contact.ContactFragment;
import com.asocialfingers.kachestvo.Tabs.Extra.ExtraFragment;
import com.asocialfingers.kachestvo.Tabs.Home.HomeFragment;
import com.asocialfingers.kachestvo.Utils.Button.CustomAnimatedButton;
import com.asocialfingers.kachestvo.Utils.Connection.AppController;
import com.asocialfingers.kachestvo.Utils.Connection.ConnectionLinks;
import com.asocialfingers.kachestvo.Utils.Database.CommentDao;
import com.asocialfingers.kachestvo.Utils.Database.CommentDetails;
import com.asocialfingers.kachestvo.Utils.Database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private String _nameSurnameHolder, _commentHolder, created_at;
    private TextView _nameSurnameCounter, _commentCounter;
    private AppBarConfiguration mAppBarConfiguration;
    private EditText _nameSurname, _comment;
    private NavigationView navigationView;
    private ProgressDialog _pDialog;
    private Dialog _customDialog;
    private Button _login, _send;
    private DrawerLayout drawer;
    private ImageButton _close;
    private Toolbar toolbar;
    private CommentDao dao;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utils();
        setSupportActionBar(toolbar);
        floatingActionButtonEvents();
        navigationControls();
        buttonEvents();
        loadFragment(new HomeFragment());
    }

    //region Floating Action Button
    private void floatingActionButtonEvents() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });
    }
    //endregion

    //region Database Connection
    private void sendComment() {
        showDialog();
        _pDialog.setMessage("Gönderiliyor ...");

        StringRequest strReq = new StringRequest(Request.Method.POST, ConnectionLinks.insertComment, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        hideDialog();
                        _customDialog.dismiss();
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);

                        long id = dao.insertComment(new CommentDetails(-1, _nameSurnameHolder, _commentHolder, created_at));

                        Toast.makeText(MainActivity.this, "Yorumunuz gönderilmiştir, teşekkürler.", Toast.LENGTH_LONG).show();
                        _customDialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Yorum gönderilirken hata oluştu! Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                _pDialog.dismiss();
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Server'da problem oluştu. Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Name", _nameSurnameHolder);
                params.put("Comment", _commentHolder);

                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq);
    }
    //endregion

    //region Fragment Controls
    private void navigationControls() {
        mAppBarConfiguration = new AppBarConfiguration.Builder
                (R.id.homeTab, R.id.aboutTab, R.id.extraTab, R.id.contactTab, R.id.commentTab)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //navigationView.setItemTextColor(ColorStateList.valueOf(getColor(R.color.text_colors_dark)));
        //navigationView.setItemIconTintList(ColorStateList.valueOf(getColor(R.color.object_color_dark)));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.homeAction:
                        fragment = new HomeFragment();
                        toolbar.setTitle("Anasayfa");
                        loadFragment(fragment);
                        return true;
                    case R.id.aboutAction:
                        fragment = new AboutFragment();
                        toolbar.setTitle("Hakkımızda");
                        loadFragment(fragment);
                        return true;
                    case R.id.extraAction:
                        fragment = new ExtraFragment();
                        toolbar.setTitle("Ekstra");
                        loadFragment(fragment);
                        return true;
                    case R.id.contactAction:
                        fragment = new ContactFragment();
                        toolbar.setTitle("İletişim");
                        loadFragment(fragment);
                        return true;
                    case R.id.commentAction:
                        fragment = new CommentsFragment();
                        toolbar.setTitle("Yorumlarım");
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        drawer.closeDrawers();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //endregion

    //region System Events
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (_customDialog.isShowing()) {
            _customDialog.dismiss();
        } else {
            @SuppressLint("ResourceType") AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Uygulamayı Kapat");
            builder.setMessage("Uygulamayı Kapamak İstediğinizden Emin Misiniz?");
            builder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            builder.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    }
    //endregion

    //region Utilities
    private void utils() {
        _login = (Button) findViewById(R.id.btnLoginHeader);
        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        _pDialog = new ProgressDialog(this);
        _pDialog.setCancelable(false);

        _customDialog = new Dialog(MainActivity.this);

        dao = new CommentDao(getApplicationContext());
        db = new DBHelper(getApplicationContext());
    }

    private void stringToTextUtil() {
        _nameSurnameHolder = _nameSurname.getText().toString();
        _commentHolder = _comment.getText().toString();
    }

    private void getTimeStamp() {
        Date calendar = Calendar.getInstance().getTime();
        created_at = calendar.toString();
    }
    //endregion

    //region Pop-Up
    private void showPopUp() {

        _customDialog.setContentView(R.layout.custom_comment_modal);

        _nameSurnameCounter = (TextView) _customDialog.findViewById(R.id.txtNameSurnameCounter);
        _commentCounter = (TextView) _customDialog.findViewById(R.id.txtCommentCounter);
        _nameSurname = (EditText) _customDialog.findViewById(R.id.txtNameSurname);
        _send = (Button) _customDialog.findViewById(R.id.btnSend);
        _comment = (EditText) _customDialog.findViewById(R.id.txtComment);
        _close = (ImageButton) _customDialog.findViewById(R.id.imgClose);

        stringToTextUtil();
        getTimeStamp();

        _nameSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int maxNameSurnameLength = 25;
                int resultNameSurnameLength;
                int nameSurnameLength;

                nameSurnameLength = _nameSurname.getText().length();

                resultNameSurnameLength = maxNameSurnameLength - nameSurnameLength;
                _nameSurnameCounter.setText(String.valueOf(resultNameSurnameLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int maxCommentLength = 350;
                int resultCommentLength;
                int commentLength;

                commentLength = _comment.getText().length();

                resultCommentLength = maxCommentLength - commentLength;
                _commentCounter.setText(String.valueOf(resultCommentLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _customDialog.dismiss();
            }
        });

        _send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringToTextUtil();
                getTimeStamp();
                if (_commentHolder.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lütfen yorum yazınız.", Toast.LENGTH_LONG).show();
                } else {
                    String fillTheName = (_nameSurnameHolder.isEmpty()) ? _nameSurnameHolder = "Ad ve Soyad Belirtilmedi." : "";
                    sendComment();
                }
            }
        });

        Objects.requireNonNull(_customDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _customDialog.show();
    }
    //endregion

    //region Dialog
    private void showDialog() {
        if (!_pDialog.isShowing())
            _pDialog.show();
    }

    private void hideDialog() {
        if (_pDialog.isShowing())
            _pDialog.dismiss();
    }
    //endregion

    //region Button
    private void buttonEvents() {
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    //endregion
}