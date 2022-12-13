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
import com.example.finalprojectdjcgrocery.adapters.ProductsAdapter;
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
    String uName;

    RecyclerView recyclerView;
    List<CartItem> cart;
//    ArrayList<String> imgList;
//    ArrayList<String> nameList;
//    ArrayList<String> priceList;
//    ArrayList<String> qtyList;
    DatabaseReference ref;
    CartItemAdapter adapter;

    double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        uName = getIntent().getStringExtra("USERNAME");

        payNowBtn = findViewById(R.id.payBtn);
        recyclerView = findViewById(R.id.rv_cart);
        price = findViewById(R.id.priceTag);

        cart = new ArrayList<CartItem>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCart();

        for (CartItem price: cart) {
            totalPrice += price.getTotal_price();
        }

        price.setText("Total Price : " + totalPrice);

//        FirebaseRecyclerOptions<CartItem> options =
//                new FirebaseRecyclerOptions.Builder<CartItem>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cart").child(uName), CartItem.class)
//                        .build();
//
//        adapter = new CartItemAdapter(options, this, uName);
//        recyclerView.setAdapter(adapter);


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
//                    adapter = new CartItemAdapter(Checkout.this, productList, uName);
//                    recyclerView.setAdapter(adapter);
//                }
//                else{
//                    Toast.makeText(Checkout.this, "Cart is Empty!", Toast.LENGTH_SHORT).show();
//                }
//            }

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

    private void loadCart() {
        FirebaseDatabase.getInstance().getReference("Cart").child(uName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CartItem cartItem = dataSnapshot.getValue(CartItem.class);
                        cartItem.setKey(dataSnapshot.getKey());
                        cart.add(cartItem);
//                        totalPrice = totalPrice + cartItem.getTotal_price();
                    }
                    //adapter.notifyDataSetChanged();
                    adapter = new CartItemAdapter(Checkout.this, cart, uName);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Could not load Products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.account:
                Toast.makeText(this, "Account is selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getApplicationContext(), MyAccount.class);
                a.putExtra("USERNAME", uName);
                startActivity(a);
                return true;
            case R.id.music:
                Toast.makeText(this, "Background Music is selected", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(getApplicationContext(), BackgroundMusic.class);
                m.putExtra("USERNAME", uName);
                startActivity(m);
                return true;
            case R.id.categories:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(getApplicationContext(), Categories.class);
                c.putExtra("USERNAME", uName);
                startActivity(c);
                return true;
            case R.id.location:
                Toast.makeText(this, "Location is selected", Toast.LENGTH_SHORT).show();
                Intent v = new Intent(getApplicationContext(), location.class);
                v.putExtra("USERNAME", uName);
                startActivity(v);
                return true;
            case R.id.logout:
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                l.putExtra("USERNAME", uName);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}