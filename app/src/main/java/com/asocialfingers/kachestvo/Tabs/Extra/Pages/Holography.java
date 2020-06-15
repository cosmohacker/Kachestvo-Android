package com.asocialfingers.kachestvo.Tabs.Extra.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.asocialfingers.kachestvo.R;

import maes.tech.intentanim.CustomIntent;

public class Holography extends AppCompatActivity {

    private ImageButton _back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_holography);

        methodCleaner();
    }

    private void methodCleaner(){
        utils();
        imageEvents();
    }

    private void utils(){
        _back = (ImageButton)findViewById(R.id.btnBack);
    }

    private void imageEvents(){
        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                CustomIntent.customType(Holography.this, "right-to-left");
            }
        });

    }
}
