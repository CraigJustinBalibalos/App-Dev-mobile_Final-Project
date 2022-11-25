package com.example.finalprojectdjcgrocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, registerBtn;

//    MediaPlayer mediaPlayer;

    ImageView mute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mute = findViewById(R.id.muteIcon);

        MusicPlayer.playAudio(this);

//        if(mediaPlayer==null){
//            mediaPlayer = MediaPlayer.create(this,R.raw.song);
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mediaPlayer.start();
//                }
//            });
//        }
//        mediaPlayer.start();

        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.pause();/
//                int position = mediaPlayer.getCurrentPosition();
                Intent i = new Intent(getApplicationContext(), Login.class);
//                i.putExtra("song", position);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaPlayer.pause();
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }

    public void stop(View view) {
//        stopPlayer();
        MusicPlayer.stopAudio(this);
    }
//
//    private void stopPlayer() {
//        if(mediaPlayer != null){
//            mediaPlayer.release();
//            mediaPlayer = null;
//            Toast.makeText(this,"MediaPlayer source is released", Toast.LENGTH_SHORT).show();
//        }
//    }
}