package com.example.finalprojectdjcgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class location extends AppCompatActivity {

    EditText source, destination;
    Button track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        source = findViewById(R.id.editTextTextPersonName);
        destination = findViewById(R.id.editTextTextPersonName2);
        track = findViewById(R.id.button);

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = source.getText().toString().trim();
                String des = destination.getText().toString().trim();
                DisplayTrack(src, des);
            }

            private void DisplayTrack(String src, String des) {
                try {

                    // call the installed google maps lib
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + src + "/" + des);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } catch (ActivityNotFoundException e) {

                }

            }
        });
    }
}