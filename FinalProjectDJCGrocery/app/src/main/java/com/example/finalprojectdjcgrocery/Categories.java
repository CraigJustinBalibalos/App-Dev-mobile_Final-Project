package com.example.finalprojectdjcgrocery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

//    MediaPlayer mediaPlayer
    RecyclerView recyclerView;
    ArrayList<String> catList;
    DatabaseReference ref;
    CategoriesAdapter adapter;
    int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        recyclerView = findViewById(R.id.recyclerViewCat);
        ref = FirebaseDatabase.getInstance().getReference().child("Category");
        catList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoriesAdapter(this, catList);
        recyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Category category = dataSnapshot.getValue(Category.class);
//                    catList.add(category);
//                    for (id = 1; id > catList.size(); id++){
//                        ref = FirebaseDatabase.getInstance().getReference().child("Category").child(id + "");
                        String category = dataSnapshot.child("category_name").getValue().toString().trim();
                        catList.add(category);
//                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        Intent getSong = getIntent();
//        int position = getSong.getIntExtra("song", 0);
//
//        if(mediaPlayer==null){
//            mediaPlayer = MediaPlayer.create(this,R.raw.song);
//            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    mediaPlayer.start();
//                }
//            });
//        }
//        mediaPlayer.seekTo(position);
//        mediaPlayer.start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        mediaPlayer.pause();
//        int position = mediaPlayer.getCurrentPosition();

        switch(item.getItemId()){
            case R.id.account:
                Toast.makeText(this, "Account is selected", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(getApplicationContext(), MyAccount.class);
//                a.putExtra("song", position);
                startActivity(a);
                return true;
            case R.id.music:
                Toast.makeText(this, "Background Music is selected", Toast.LENGTH_SHORT).show();
                Intent m = new Intent(getApplicationContext(), BackgroundMusic.class);
//                m.putExtra("song", position);
                startActivity(m);
                return true;
            case R.id.categories:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent c = new Intent(getApplicationContext(), Categories.class);
//                c.putExtra("song", position);
                startActivity(c);
                return true;
            case R.id.logout:
                Toast.makeText(this, "Categories is selected", Toast.LENGTH_SHORT).show();
                Intent l = new Intent(getApplicationContext(), MainActivity.class);
//                l.putExtra("song", position);
                startActivity(l);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}