package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;

    String uName ,uPass;

    ImageView mute;

    DatabaseReference ref;

//    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Intent getSong = getIntent();
//        int position = getSong.getIntExtra("song", 0);

//        if(mediaPlayer==null){
//            mediaPlayer = MediaPlayer.create(this,R.raw.song);
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mediaPlayer.start();
//                }
//            });
//        }
//        mediaPlayer.seekTo(position);
//        mediaPlayer.start();

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);
        mute = findViewById(R.id.muteIcon);

        loginBtn = findViewById(R.id.loginBtn2);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref = FirebaseDatabase.getInstance().getReference().child("User").child("1");
                //ref = FirebaseDatabase.getInstance().getReference().child("User").child(name);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        uName = snapshot.child("username").getValue().toString().trim();
                        uPass = snapshot.child("password").getValue().toString().trim();

                        if(uName.equals(username.getText().toString().trim()) && uPass.equals(password.getText().toString().trim())){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                            mediaPlayer.pause();
//                            int position = mediaPlayer.getCurrentPosition();
                            Intent i = new Intent(getApplicationContext(), Categories.class);
//                            i.putExtra("song", position);
                            startActivity(i);
                        }
                        else if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                            Toast.makeText(Login.this, "Please Enter Valid Info", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void stop(View view) {
//        stopPlayer();
        MusicPlayer.stopAudio(this);
    }

//    private void stopPlayer() {
//        if(mediaPlayer != null){
//            mediaPlayer.release();
//            mediaPlayer = null;
//            Toast.makeText(this,"MediaPlayer source is released", Toast.LENGTH_SHORT).show();
//        }
//    }
}