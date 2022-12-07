package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AdminAddCategory extends AppCompatActivity {
    EditText catName;
    Button img, confirm;
    RecyclerView rv_cat;

    ArrayList<String> catList;
    ArrayList<String> imgList;

    DatabaseReference ref;
    CategoriesAdapter adapter;
    Category category;
    long maxID = 0;

    //image
    StorageReference mstorageRef;
    public Uri imguri;
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);
        catName = findViewById(R.id.newCatName);
        img = findViewById(R.id.browseImgBtn);
        confirm = findViewById(R.id.confirmNewCatBtn);
        rv_cat = findViewById(R.id.recyclerview_checkCat);

        mstorageRef = FirebaseStorage.getInstance().getReference("Images");

        ref = FirebaseDatabase.getInstance().getReference().child("Category");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(AdminAddCategory.this, "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                    if(!catName.getText().toString().equals("")){
                        category.setName(catName.getText().toString());
                        category.setImg(mstorageRef.child(System.currentTimeMillis()
                                + "." + getExtension(imguri)) + "");
                        ref.child(String.valueOf(maxID + 1)).setValue(category);
                        Toast.makeText(getApplicationContext(), "Category Added", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        rv_cat.setLayoutManager(new LinearLayoutManager(this));

        catList = new ArrayList<String>();
        imgList = new ArrayList<String>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                        Category cat = dataSnapshot.getValue(Category.class);
//                        catList.add(cat);
                    String categoryName = dataSnapshot.child("name").getValue().toString().trim();
                    catList.add(categoryName);
                    String categoryImg = dataSnapshot.child("img").getValue().toString().trim();
                    imgList.add(categoryImg);
                }
                //adapter.notifyDataSetChanged();
                adapter = new CategoriesAdapter(AdminAddCategory.this, catList, imgList);
                rv_cat.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminAddCategory.this, "Unable to load categories", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //image
    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Fileuploader() {
        StorageReference ref = mstorageRef.child(System.currentTimeMillis()
                + "." + getExtension(imguri) );
        uploadTask = ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(AdminAddCategory.this,
                                "Image uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(AdminAddCategory.this, "Fail to upload", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    //menu
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