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
import android.widget.TextView;
import android.widget.Toast;

public class OrderConfirmation extends AppCompatActivity {
    //show total missing

    Button logout, shop;
    TextView confirmTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        logout = findViewById(R.id.logoutBtn);
        shop = findViewById(R.id.returnBtn);
        confirmTxt = findViewById(R.id.orderConfirmMessage);

        confirmTxt.setText("Order has been placed! Total Price: ");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderConfirmation.this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l = new Intent(getApplicationContext(), Categories.class);
                startActivity(l);
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
            case R.id.logout:
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}