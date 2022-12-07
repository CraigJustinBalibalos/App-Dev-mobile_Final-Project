package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> prodImg;
    DatabaseReference ref;
    ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        prodImg = new ArrayList<String>();

        ref = FirebaseDatabase.getInstance().getReference().child("Product");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    String categoryImg = dataSnapshot.child("product_img").getValue().toString().trim();
                    prodImg.add(categoryImg);
                }
                //adapter.notifyDataSetChanged();
                adapter = new ProductsAdapter(Products.this, prodImg);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Products.this, "Unable to load categories", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}