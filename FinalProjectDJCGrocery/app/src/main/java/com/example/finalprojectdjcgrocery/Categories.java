package com.example.finalprojectdjcgrocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> catList;
    ArrayList<String> imgList;
    DatabaseReference ref;
    CategoriesAdapter adapter;
    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent getName = getIntent();
        uName = getName.getStringExtra("USERNAME");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        catList = new ArrayList<String>();
        imgList = new ArrayList<String>();

        ref = FirebaseDatabase.getInstance().getReference().child("Category");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                        Category cat = dataSnapshot.getValue(Category.class);
//                        catList.add(cat);
                    String categoryName = dataSnapshot.child("category_name").getValue().toString().trim();
                    catList.add(categoryName);
                    String categoryImg = dataSnapshot.child("category_img").getValue().toString().trim();
                    imgList.add(categoryImg);
                }
                //adapter.notifyDataSetChanged();
                adapter = new CategoriesAdapter(Categories.this, catList, imgList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Categories.this, "Unable to load categories", Toast.LENGTH_SHORT).show();
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
            case R.id.logout:
                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayProducts(View view) {

    }
}