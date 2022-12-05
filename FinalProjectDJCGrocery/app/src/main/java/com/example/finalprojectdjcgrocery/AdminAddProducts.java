package com.example.finalprojectdjcgrocery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AdminAddProducts extends AppCompatActivity {
    EditText pName, desc, price;
    Button imgBtn, confirm;
    ImageView img;

    //image
    StorageReference mstorageRef;
    public Uri imguri;
    private StorageTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);
        pName = findViewById(R.id.newProName);
        desc = findViewById(R.id.newProDes);
        price = findViewById(R.id.newProPrice);
        imgBtn = findViewById(R.id.newProBrowseImgBtn);
        confirm = findViewById(R.id.newProConfirmBtn);

        mstorageRef = FirebaseStorage.getInstance().getReference("Images");
        img= findViewById(R.id.newProImg);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileChooser();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(AdminAddProducts.this, "Upload in progress", Toast.LENGTH_LONG).show();

                } else {
                    Fileuploader();
                }
            }
        });

    }

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
                        Toast.makeText(AdminAddProducts.this,
                                "Image uploaded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(AdminAddProducts.this, "Fail to upload.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void FileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK && data !=null && data.getData()!= null)
        {
            imguri = data.getData();
            img.setImageURI(imguri);

        }
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