package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;

    String uName;
    String uPass;
    String name;
    String pass;

    DatabaseReference ref;
    ArrayList<String> arrayList = new ArrayList<>();
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputPassword);

        loginBtn = findViewById(R.id.loginBtn2);

        //name = username.getText().toString();
        //pass = password.getText().toString();

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
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Categories.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(Login.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
//            mAuth.signInWithEmailAndPassword(name, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("UserPass", "signIn:success");
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        updateUI(user);
//                        Intent i = new Intent(getApplicationContext(), Categories.class);
//                        startActivity(i);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("UserPass", "signIn:failure", task.getException());
//                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//                        updateUI(null);
//                    }
//                }
//            });
        }

    private void updateUI(FirebaseUser user) {
    }
}