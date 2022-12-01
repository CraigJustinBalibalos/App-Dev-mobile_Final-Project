package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccount extends AppCompatActivity {

    Button next;
    TextView nameTV;

    MediaPlayer mediaPlayer;
    DatabaseReference ref;

    String uName;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Intent getName = getIntent();
        uName = getName.getStringExtra("USERNAME");

        nameTV = findViewById(R.id.usernameTextView);
        nameTV.setText("Username: " + uName);


        Intent getSong = getIntent();
        int resume = getSong.getIntExtra("song", 0);

        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.song);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
        mediaPlayer.seekTo(resume);
        mediaPlayer.start();

        next = findViewById(R.id.nextBtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                int position = mediaPlayer.getCurrentPosition();
                Intent n = new Intent(getApplicationContext(), ModifyUserInfo.class);
                n.putExtra("song", position);
                startActivity(n);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.account:
                Toast.makeText(this, "Account is selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getApplicationContext(), MyAccount.class);
                a.putExtra("USERNAME", uName);
                startActivity(a);
                return true;
            case R.id.music:
                Toast.makeText(this, "Background Music is selected", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(getApplicationContext(), BackgroundMusic.class);
                m.putExtra("USERNAME", uName);
                startActivity(m);
                return true;
            case R.id.categories:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(getApplicationContext(), Categories.class);
                c.putExtra("USERNAME", uName);
                startActivity(c);
                return true;
            case R.id.logout:
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}