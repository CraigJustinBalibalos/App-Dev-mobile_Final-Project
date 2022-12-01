package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText username, password, confirmPass;
    Button registerBtn;

    long maxID = 0;

    DatabaseReference ref;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user = new User();

        username = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        confirmPass = findViewById(R.id.registerConfirmPassword);

        registerBtn = findViewById(R.id.createAccountBtn);

        ref = FirebaseDatabase.getInstance().getReference().child("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!username.getText().toString().equals("") && !password.getText().toString().equals("") && !confirmPass.getText().toString().equals("")){
                    if(password.getText().toString().equals(confirmPass.getText().toString())) {
                        user.setUsername(username.getText().toString());
                        user.setPassword(password.getText().toString());
                        user.setRole("user");
                        ref.child(String.valueOf(maxID + 1)).setValue(user);
                        Toast.makeText(getApplicationContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void stop(View view) {
        MusicPlayer.stopAudio(this);
    }

}