package com.asocialfingers.kachestvo.Tabs.Extra;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.asocialfingers.kachestvo.R;
import com.asocialfingers.kachestvo.Tabs.Extra.Pages.Desktop;
import com.asocialfingers.kachestvo.Tabs.Extra.Pages.Holography;
import com.asocialfingers.kachestvo.Tabs.Extra.Pages.Mobile;
import com.asocialfingers.kachestvo.Tabs.Extra.Pages.Slider;

import maes.tech.intentanim.CustomIntent;

public class ExtraFragment extends Fragment {

    //Bu kısımda porje için gerekli olan sarf malzemeler ve komponentler yer alacak.
    private ImageButton holographyImage, sliderImage, pcImage, mobileImage;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_extra, container, false);

        methodCleaner(root);

        return root;
    }

    private void methodCleaner(View root) {
        utils(root);
        imageEvents();
    }

    private void utils(View root) {
        holographyImage = (ImageButton) root.findViewById(R.id.holographyImage);
        sliderImage = (ImageButton)root.findViewById(R.id.sliderImage);
        pcImage = (ImageButton)root.findViewById(R.id.pcImage);
        mobileImage = (ImageButton)root.findViewById(R.id.mobileImage);
    }


    private void imageEvents() {
        holographyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Holography.class);
                startActivity(i);
                CustomIntent.customType(getActivity(), "left-to-right");
            }
        });

        sliderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Slider.class);
                startActivity(i);
                CustomIntent.customType(getActivity(), "left-to-right");
            }
        });

        pcImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Desktop.class);
                startActivity(i);
                CustomIntent.customType(getActivity(), "left-to-right");
            }
        });

        mobileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Mobile.class);
                startActivity(i);
                CustomIntent.customType(getActivity(), "left-to-right");
            }
        });
    }

}