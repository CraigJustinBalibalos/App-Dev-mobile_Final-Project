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

import com.example.finalprojectdjcgrocery.adapters.OrdersAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyAccount extends AppCompatActivity {

    Button next;
    TextView nameTV;
    String uName;

    RecyclerView recyclerView;
    ArrayList<String> dateList;
    ArrayList<String> deliveryList;
    ArrayList<String> priceList;
    DatabaseReference ref;
    OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        recyclerView = findViewById(R.id.recyclerView_orderHistory);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //need it for order history
//        ref = FirebaseDatabase.getInstance().getReference().child("Order");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    String date = dataSnapshot.child("order_date").getValue().toString().trim();
//                    dateList.add(date);
//                    String deliveryMethod = dataSnapshot.child("delivery").getValue().toString().trim();
//                    deliveryList.add(deliveryMethod);
//                    String total = dataSnapshot.child("order_price").getValue().toString().trim();
//                    priceList.add(total);
//                }
//                adapter = new OrdersAdapter(MyAccount.this, dateList, deliveryList, priceList);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(MyAccount.this, "Unable to load order history", Toast.LENGTH_SHORT).show();
//            }
//        });

//        String user = login.currentUser.getUsername();
//
//        String user = login.uName;

        Intent getName = getIntent();
        uName = getName.getStringExtra("USERNAME");
//
//        uName = user;

        nameTV = findViewById(R.id.usernameTextView);
        nameTV.setText("Username: " + uName);

        next = findViewById(R.id.nextBtn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(getApplicationContext(), ModifyUserInfo.class);
                n.putExtra("USERNAME", uName);
                startActivity(n);
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
            case R.id.location:
                Toast.makeText(this, "Location is selected", Toast.LENGTH_SHORT).show();
                Intent v = new Intent(getApplicationContext(), location.class);
                startActivity(v);
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