package com.asocialfingers.kachestvo.Tabs.About;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.asocialfingers.kachestvo.R;

import org.w3c.dom.Text;

import static android.widget.RelativeLayout.CENTER_HORIZONTAL;

public class AboutFragment extends Fragment {

    private TextView _asocialfingers;
    private LinearLayout _about;
    private int margin = -75;
    private ImageView _logo;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        utils(root);
        timerTasks();

        return root;
    }

    private void utils(View view) {
        _asocialfingers = (TextView) view.findViewById(R.id.lblaSocialFingers);
        _about = (LinearLayout) view.findViewById(R.id.linearAbout);
        _logo = (ImageView) view.findViewById(R.id.imgLogo);
    }

    private void timerTasks() {
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
                _logo.setLayoutParams(new LinearLayout.LayoutParams(400, 400));
                _asocialfingers.setTextSize(18);
            }
        }.start();

        new CountDownTimer(1100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(1100);
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
                _logo.animate().translationY(margin).start();
                _asocialfingers.animate().translationY(margin).start();
            }
        }.start();

        new CountDownTimer(1300, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(1300);
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
                _about.setVisibility(View.VISIBLE);
                Animation animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                _about.startAnimation(animFadeOut);
                _about.animate().translationY(margin).start();
            }
        }.start();



    }

}
