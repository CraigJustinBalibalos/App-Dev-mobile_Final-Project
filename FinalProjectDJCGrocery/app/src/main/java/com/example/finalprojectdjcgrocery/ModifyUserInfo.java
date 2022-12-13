package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalprojectdjcgrocery.pojo.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ModifyUserInfo extends AppCompatActivity {

    EditText username, oldPass, password, confpass;
    Button changeName, changePass, backBtn;

    String uPass, uName, uRole, confName;

    User user;

    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_info);

        confName = getIntent().getStringExtra("USERNAME");

        username = findViewById(R.id.newUsername);
        oldPass = findViewById(R.id.oldPassword);
        password = findViewById(R.id.newPassword);
        confpass = findViewById(R.id.confirmPassword);

        changeName = findViewById(R.id.confirmUsernameBtn);
        changePass = findViewById(R.id.confirmPasswordBtn);
        backBtn = findViewById(R.id.button6);

        username.setText(confName);

        user = new User();

        changeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Query userQuery = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("username");

                userQuery.equalTo(confName).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    // result
                                    String key = userSnapshot.getKey();
                                    HashMap User = new HashMap();
                                    User.put("username", username.getText().toString());
                                    DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("User");
                                    updateRef.child(key).updateChildren(User).addOnSuccessListener(new OnSuccessListener() {
                                        @Override
                                        public void onSuccess(Object o) {
                                            Toast.makeText(ModifyUserInfo.this, "Username updated!", Toast.LENGTH_SHORT).show();
                                            Intent a = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(a);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!oldPass.getText().toString().equals("")&& !password.getText().toString().equals("")&& !confpass.getText().toString().equals("")) {
                    if (password.getText().toString().equals(confpass.getText().toString())) {
                        final Query userQuery = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("username");
                        final Query passQuery = FirebaseDatabase.getInstance().getReference().child("User").orderByChild("password");

                        userQuery.equalTo(confName).addListenerForSingleValueEvent(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                            // result
                                            String key = userSnapshot.getKey();
                                            passQuery.equalTo(oldPass.getText().toString()).addListenerForSingleValueEvent(
                                                    new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                                                // result
                                                                String key2 = userSnapshot.getKey();
                                                                if (key2 == key) {
                                                                    HashMap User = new HashMap();
                                                                    User.put("password", password.getText().toString());
                                                                    DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("User");
                                                                    updateRef.child(key2).updateChildren(User).addOnSuccessListener(new OnSuccessListener() {
                                                                        @Override
                                                                        public void onSuccess(Object o) {
                                                                            Toast.makeText(ModifyUserInfo.this, "Password updated!", Toast.LENGTH_SHORT).show();
                                                                            Intent a = new Intent(getApplicationContext(), MainActivity.class);
                                                                            startActivity(a);
                                                                        }
                                                                    });
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                    } else {
                        Toast.makeText(ModifyUserInfo.this, "Password not match!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ModifyUserInfo.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyAccount.class);
                i.putExtra("USERNAME", confName);
                startActivity(i);
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
                startActivity(a);
                return true;
            case R.id.music:
                Toast.makeText(this, "Background Music is selected", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(getApplicationContext(), BackgroundMusic.class);
                startActivity(m);
                return true;
            case R.id.categories:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(getApplicationContext(), Categories.class);
                startActivity(c);
                return true;
            case R.id.location:
                Toast.makeText(this, "Location is selected", Toast.LENGTH_SHORT).show();
                Intent v = new Intent(getApplicationContext(), location.class);
                startActivity(v);
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