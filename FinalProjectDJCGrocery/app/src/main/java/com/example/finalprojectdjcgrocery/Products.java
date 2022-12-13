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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalprojectdjcgrocery.adapters.ProductsAdapter;
import com.example.finalprojectdjcgrocery.pojo.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Products extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView viewCart;

    List<Product> products;
//    ArrayList<String> prodImg;

    DatabaseReference ref;
    ProductsAdapter adapter;

    public String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        viewCart = findViewById(R.id.viewCart);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        products = new ArrayList<Product>();
//        products = new ArrayList<String>();

        uName = getIntent().getStringExtra("USERNAME");

        loadProducts();

//        ref = FirebaseDatabase.getInstance().getReference().child("Product");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        Product categoryImg = dataSnapshot.child("product_img").getValue();
//                        prodImg.add(categoryImg);
//                    }
//                    //adapter.notifyDataSetChanged();
//                    adapter = new ProductsAdapter(Products.this, prodImg);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Products.this, "Unable to load Products", Toast.LENGTH_SHORT).show();
//            }
//        });

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Checkout.class);
                i.putExtra("USERNAME", uName);
                startActivity(i);
            }
        });

    }

    private void loadProducts() {
        FirebaseDatabase.getInstance().getReference("Product").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Product product = dataSnapshot.getValue(Product.class);
                        product.setKey(dataSnapshot.getKey());
                        products.add(product);
                    }
                    //adapter.notifyDataSetChanged();
                    adapter = new ProductsAdapter(Products.this, products);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Products.this, "Could not load Products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    public void addItemToCart(View view) {
        final Query prodQuery = FirebaseDatabase.getInstance().getReference().child("Product").orderByChild("name");
        prodQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot prodSnapshot: snapshot.getChildren()){
                    String key = prodSnapshot.getKey();
                    HashMap Prod = new HashMap();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}