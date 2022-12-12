package com.example.finalprojectdjcgrocery.admin_pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finalprojectdjcgrocery.MainActivity;
import com.example.finalprojectdjcgrocery.R;
import com.example.finalprojectdjcgrocery.adapters.AdminViewOrdersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminViewOrders extends AppCompatActivity {
    RecyclerView rv_orders;

    RecyclerView recyclerView;
    ArrayList<String> userList;
    ArrayList<String> dateList;
    ArrayList<String> deliveryList;
    ArrayList<String> priceList;
    DatabaseReference ref;
    AdminViewOrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_orders);
        rv_orders = findViewById(R.id.rv_adminViewOrders);

        ref = FirebaseDatabase.getInstance().getReference().child("Order");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String user = dataSnapshot.child("username").getValue().toString().trim();
                    userList.add(user);
                    String date = dataSnapshot.child("order_date").getValue().toString().trim();
                    dateList.add(date);
                    String deliveryMethod = dataSnapshot.child("delivery").getValue().toString().trim();
                    deliveryList.add(deliveryMethod);
                    String total = dataSnapshot.child("order_price").getValue().toString().trim();
                    priceList.add(total);
                }
                adapter = new AdminViewOrdersAdapter(AdminViewOrders.this, userList, dateList, deliveryList, priceList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminViewOrders.this, "Unable to load order history", Toast.LENGTH_SHORT).show();
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