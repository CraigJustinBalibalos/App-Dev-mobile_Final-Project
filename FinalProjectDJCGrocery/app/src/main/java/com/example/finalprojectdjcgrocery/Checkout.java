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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectdjcgrocery.adapters.CartItemAdapter;
import com.example.finalprojectdjcgrocery.pojo.CartItem;
import com.example.finalprojectdjcgrocery.pojo.Product;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {
    //pass total price missing;
    Button payNowBtn;
    TextView price;

    RecyclerView recyclerView;
    List<CartItem> productList;
//    ArrayList<String> imgList;
//    ArrayList<String> nameList;
//    ArrayList<String> priceList;
//    ArrayList<String> qtyList;
    DatabaseReference ref;
    CartItemAdapter adapter;

    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        uName = getIntent().getStringExtra("USERNAME");

        payNowBtn = findViewById(R.id.payBtn);
        recyclerView = findViewById(R.id.rv_cart);
        price = findViewById(R.id.priceTag);

        price.setText("Total Price : ");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CartItem> options =
                new FirebaseRecyclerOptions.Builder<CartItem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart"), CartItem.class)
                        .build();

        adapter = new CartItemAdapter(options);
        recyclerView.setAdapter(adapter);


//        ref = FirebaseDatabase.getInstance().getReference().child("Cart").child(uName);
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////                    String img = dataSnapshot.child("product_img").getValue().toString().trim();
////                    imgList.add(img);
////                    String pName = dataSnapshot.child("product_name").getValue().toString().trim();
////                    nameList.add(pName);
////                    String price = dataSnapshot.child("product_price").getValue().toString().trim();
////                    priceList.add(price);
////                    String qty = dataSnapshot.child("product_qty").getValue().toString().trim();
////                    qtyList.add(qty);
//                        CartItem product = dataSnapshot.getValue(CartItem.class);
//                        product.setKey(dataSnapshot.getKey());
//                        productList.add(product);
//                    }
//                    adapter = new CartItemAdapter(Checkout.this, productList);
//                    recyclerView.setAdapter(adapter);
//                }
//                else{
//                    Toast.makeText(Checkout.this, "Cart is Empty!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Checkout.this, "Unable to load cart products", Toast.LENGTH_SHORT).show();
//            }
//        });

        payNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext(), Delivery.class);
                startActivity(a);
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