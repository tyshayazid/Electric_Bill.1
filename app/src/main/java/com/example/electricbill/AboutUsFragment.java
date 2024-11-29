package com.example.electricbill;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

public class AboutUsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        // Find the MaterialButton by its ID
        MaterialButton btnLink = view.findViewById(R.id.btn_link);

        // Set OnClickListener for the MaterialButton
        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a link in a browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com"));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}
