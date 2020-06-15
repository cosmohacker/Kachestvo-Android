package com.asocialfingers.kachestvo.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private String defUsername = "BT-7274", defPassword = "LugerP08";
    private RelativeLayout _relativeLogin;
    private EditText _username, _password;
    private LinearLayout _layoutLogin;
    private ImageButton _back;
    private ImageView _flexy;
    private Button _login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        utils();
        buttonEvents();
        timerTask();
    }

    private void utils() {
        _relativeLogin = (RelativeLayout) findViewById(R.id.relativeLogin);

        _layoutLogin = (LinearLayout) findViewById(R.id.layoutLogin);

        _username = (EditText) findViewById(R.id.txtUsername);
        _password = (EditText) findViewById(R.id.txtPassword);

        _flexy = (ImageView) findViewById(R.id.imgFlexy);

        _back = (ImageButton)findViewById(R.id.imgBack);

        _login = (Button) findViewById(R.id.btnLogin);
    }

    private void buttonEvents() {
        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_username.getText().toString().equals(defUsername) && _password.getText().toString().equals(defPassword)) {
                    Intent i = new Intent(LoginActivity.this, AdministrationActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    customSnackbar();
                }
            }
        });

        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void customSnackbar() {
        Snackbar snackbar = Snackbar
                .make(_relativeLogin, "Please check credentials!", Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.setActionTextColor(getColor(R.color.login_text_color));
        View sbView = snackbar.getView();
        snackbar.setTextColor(getColor(R.color.login_text_color));
        snackbar.setBackgroundTint(getColor(R.color.login_snackbar_background));
        snackbar.show();
    }

    private void imageEvents() {
        _flexy.animate().translationY(-500).start();
    }

    private void timerTask() {
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                };
                timer.start();
            }

            @Override
            public void onFinish() {
                imageEvents();
                _layoutLogin.setVisibility(View.VISIBLE);
                _back.setVisibility(View.VISIBLE);
                Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
                _layoutLogin.startAnimation(animFadeOut);
                _back.startAnimation(animFadeOut);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}