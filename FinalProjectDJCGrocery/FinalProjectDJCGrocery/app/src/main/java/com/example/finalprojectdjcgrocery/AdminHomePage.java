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
import android.widget.Toast;

public class AdminHomePage extends AppCompatActivity {
    Button view,addCat, addPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        view = findViewById(R.id.viewOrdersBtn);
        addCat = findViewById(R.id.addCatBtn);
        addPro = findViewById(R.id.addProductBtn);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), AdminViewOrders.class);
                startActivity(a);
            }
        });
        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), AdminAddCategory.class);
                startActivity(a);
            }
        });

        addPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), AdminAddProducts.class);
                startActivity(a);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.admin_homePage:
                Toast.makeText(this, "Home page is selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getApplicationContext(), AdminHomePage.class);
                startActivity(a);
                return true;
            case R.id.adminMusic:
                Toast.makeText(this, "Background Music is selected", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(getApplicationContext(), AdminBackgroundMusic.class);
                startActivity(m);
                return true;
            case R.id.admin_logout:
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}