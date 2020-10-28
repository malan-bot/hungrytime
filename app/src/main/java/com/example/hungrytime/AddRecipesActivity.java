package com.example.hungrytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddRecipesActivity extends AppCompatActivity {

    Button add;
    ImageView img;
    EditText name,ingredients,qty,metric;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_recipes);
        firebaseStorage= FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        db=FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.setCancelable( false );
        name=findViewById( R.id.Addname );
        ingredients=findViewById( R.id.Addingredients );
        qty=findViewById( R.id.Addqty );
        metric=findViewById( R.id.Addmetric );
        img=findViewById( R.id.Addimg );
        add=findViewById( R.id.add );
        add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _name=name.getText().toString();
                String _ingredients=ingredients.getText().toString();
                String _qty=qty.getText().toString();
                String _metric=metric.getText().toString();
                if(!_name.isEmpty() && !_ingredients.isEmpty() && !_qty.isEmpty() && !_metric.isEmpty() && !URL.isEmpty()){
                    addToDatabase(_name,_ingredients,_metric,_qty,URL);
                }else {
                    Toast.makeText( AddRecipesActivity.this, "Enter All Details", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        img.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        } );
    }

    private void addToDatabase(String name, String ingredients, String metric, String qty, String url) {
        progressDialog.show();
        Map<String,Object> map=new HashMap<>();
        map.put( "name",name );
        map.put( "ingredients",ingredients );
        map.put( "metric",metric );
        map.put( "qty",qty );
        map.put( "image",url );
        db.collection( "New Recipes" ).document(name)
                .set( map ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText( AddRecipesActivity.this, "Added !", Toast.LENGTH_SHORT ).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText( AddRecipesActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    Bitmap bitmap;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1) {
            Uri imageUri = data.getData();
            try {
                uploadFile( imageUri );
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                img.setImageBitmap( bitmap );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ProgressDialog progressDialog;
    String URL="";
    private void uploadFile(Uri data1) {
        progressDialog.show();
        //if there is a file to upload
        if (data1 != null) {
            //displaying a progress dialog while upload is going on


            final StorageReference riversRef = storageReference.child( "uploads/"+System.currentTimeMillis()+".jpg");
            riversRef.putFile(data1)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            riversRef.getDownloadUrl().addOnCompleteListener( new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful())
                                    {
                                        URL=task.getResult().toString();
                                        progressDialog.dismiss();


                                    }else
                                    {
                                        Toast.makeText( AddRecipesActivity.this, "Something went Wrong", Toast.LENGTH_SHORT ).show();

                                    }
                                }
                            } );


                            progressDialog.dismiss();
                            //and displaying a success toast
                            Toast.makeText( AddRecipesActivity.this, "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(AddRecipesActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });

        }
        //if there is not any file
        else {
            //you can display an error toast
            Toast.makeText( this, "Error !", Toast.LENGTH_SHORT ).show();
        }
    }
}