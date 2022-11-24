package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
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
            case R.id.logout:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}