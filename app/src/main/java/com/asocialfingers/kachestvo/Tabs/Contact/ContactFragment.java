package com.asocialfingers.kachestvo.Tabs.Contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Utils.Button.CustomAnimatedButton;
import com.asocialfingers.kachestvo.Utils.Connection.AppController;
import com.asocialfingers.kachestvo.Utils.Connection.ConnectionLinks;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ContactFragment extends Fragment {

    private TextView _nameCounter, _emailCounter, _subjectCounter, _contact;
    private static String TAG = ContactFragment.class.getSimpleName();
    private String nameHolder, emailHolder, subjectHolder;
    private EditText _name, _email, _subject;
    private CustomAnimatedButton _send;
    private LinearLayout _layout;
    private int mBtCounter1 = 1;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact, container, false);

        utils(root);
        textCounter();
        holderUtils();
        buttonEvents();
        timerTask();

        return root;
    }

    private void textCounter() {
        _name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int maxNameLength = 25;
                int resultNameLength;
                int nameLength;

                nameLength = _name.getText().length();

                resultNameLength = maxNameLength - nameLength;
                _nameCounter.setText(String.valueOf(resultNameLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int maxEmailLength = 50;
                int resultEmailLength;
                int emailLength;

                emailLength = _email.getText().length();

                resultEmailLength = maxEmailLength - emailLength;
                _emailCounter.setText(String.valueOf(resultEmailLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        _subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int maxSubjectLength = 500;
                int resultSubjectLength;
                int subjectLength;

                subjectLength = _subject.getText().length();

                resultSubjectLength = maxSubjectLength - subjectLength;
                _subjectCounter.setText(String.valueOf(resultSubjectLength));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void buttonEvents() {
        _send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holderUtils();
                if (!emailHolder.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    Toast.makeText(getActivity(),"Lütfen e-mail adresiniz doğru girdiğinizden emin olun!",Toast.LENGTH_LONG).show();
                }else if(nameHolder.isEmpty()){
                    Toast.makeText(getActivity(),"Lütfen size hitap edebilmemiz için isim belirtin.",Toast.LENGTH_LONG).show();
                } else if (subjectHolder.isEmpty()){
                    Toast.makeText(getActivity(),"Lütfen mesajınızı(konu) girmeyi unutmayın.",Toast.LENGTH_LONG).show();
                }else{
                    buttonToSuccess(_send);
                    sendEmail();
                }
            }
        });
    }

    private void sendEmail() {
        StringRequest strReq = new StringRequest(Request.Method.POST, ConnectionLinks.sendMail, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        buttonToSquare(_send,100);
                        Toast.makeText(getActivity(), "Mailiniz gönderilmiştir, teşekkürler.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Mail gönderilirken hata oluştu! Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Server'da problem oluştu. Daha sonra tekrar deneyin.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Name", nameHolder);
                params.put("Email", emailHolder);
                params.put("Subject", subjectHolder);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq);
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
                _contact.animate().translationY(-700).start();
                _layout.setVisibility(View.VISIBLE);
                Animation animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
                _layout.startAnimation(animFadeOut);
            }
        }.start();
    }

    //region Animation & Button Events

    private void onAnimatedButtonClicked(final CustomAnimatedButton _send) {
        if (mBtCounter1 == 0) {
            mBtCounter1++;
            buttonToSquare(_send, R.integer.bt_animation);
        } else if (mBtCounter1 == 1) {
            mBtCounter1 = 0;
            buttonToSuccess(_send);
        }
    }

    private void buttonToSquare(final CustomAnimatedButton btnMorph, int duration) {
        CustomAnimatedButton.Params square = CustomAnimatedButton.Params.create()
                .duration(duration)
                .cornerRadius(10)
                .width(150)
                .height(35)
                .color(R.color.custom_modal_button_dark)
                .colorPressed(R.color.custom_modal_button_dark)
                .text("Gonder");
        btnMorph.animation(square);
    }

    private void buttonToSuccess(final CustomAnimatedButton btnMorph) {
        CustomAnimatedButton.Params circle = CustomAnimatedButton.Params.create()
                .duration(500)
                .cornerRadius(40)
                .width(100)
                .height(100)
                .color(R.color.custom_modal_button_dark)
                .colorPressed(R.color.custom_modal_button_dark)
                .icon(R.drawable.tick_custom_button_dark);
        btnMorph.animation(circle);
    }
    //endregion

    //region Utils
    private void utils(View view) {
        _subjectCounter = (TextView) view.findViewById(R.id.lblSubject);
        _emailCounter = (TextView) view.findViewById(R.id.lblEmail);
        _nameCounter = (TextView) view.findViewById(R.id.lblName);
        _contact = (TextView) view.findViewById(R.id.lblContact);

        _subject = (EditText) view.findViewById(R.id.txtSubject);
        _email = (EditText) view.findViewById(R.id.txtEmail);
        _name = (EditText) view.findViewById(R.id.txtName);

        _send = (CustomAnimatedButton) view.findViewById(R.id.btnSend);

        _layout = (LinearLayout) view.findViewById(R.id.linearContact);
    }

    private void holderUtils() {
        subjectHolder = _subject.getText().toString();
        emailHolder = _email.getText().toString().trim();
        nameHolder = _name.getText().toString();
    }
    //endregion
}
