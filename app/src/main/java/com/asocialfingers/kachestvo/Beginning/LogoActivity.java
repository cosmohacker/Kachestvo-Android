package com.asocialfingers.kachestvo.Beginning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.asocialfingers.kachestvo.Main.MainActivity;
import com.asocialfingers.kachestvo.R;

public class LogoActivity extends AppCompatActivity {

    private RelativeLayout _logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        _logo =(RelativeLayout)findViewById(R.id.relativeLogo);

        timerTask();
    }

    private void timerTask(){
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(3000);
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
                Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                _logo.startAnimation(animFadeIn);

                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Thread timer = new Thread() {
                            public void run() {
                                try {
                                    sleep(3000);
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
                        Intent i = new Intent(LogoActivity.this, MainActivity.class);
                        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(LogoActivity.this).toBundle());
                        startActivity(i);
                    }
                }.start();

            }
        }.start();



    }
}