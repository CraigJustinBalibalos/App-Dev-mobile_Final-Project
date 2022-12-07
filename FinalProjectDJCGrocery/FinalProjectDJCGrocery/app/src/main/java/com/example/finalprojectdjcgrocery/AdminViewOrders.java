package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AdminViewOrders extends AppCompatActivity {
    RecyclerView rv_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_orders);
        rv_orders = findViewById(R.id.rv_adminViewOrders);
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