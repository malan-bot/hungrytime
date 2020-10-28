package com.example.hungrytime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {
    private EditText etUserName, etUserPassword, etUserEmail;
    private Button btnRegister;
    TextView tvUserLogin;
    String name, email, Password;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private CollectionReference usersRef;

    //////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        /////
        etUserName = findViewById(R.id.etUserName);
        etUserPassword = findViewById(R.id.etUserPassword);
        etUserEmail = findViewById(R.id.etUserEmail);
        btnRegister = findViewById(R.id.btnRegister);
        tvUserLogin = findViewById(R.id.tvUserLogin);


        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        usersRef = db.collection("Users");

        /////
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    //Upload data to database
                    String user_email = etUserEmail.getText().toString().trim();
                    String user_password = etUserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendEmailVerification();
                                sendUserData();
                                //usersRef.document(firebaseAuth.getUid());
                            } else {
                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        /////

        /////
        tvUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
        /////

    }
    //////////////////////////////////////////////////////////////////////////


    private Boolean validate(){
        Boolean result = false;

        name = etUserName.getText().toString();
        email = etUserEmail.getText().toString();
        Password = etUserPassword.getText().toString();

        if(name.isEmpty()) {
            etUserName.setError("Please enter your name");
            etUserName.requestFocus();
        } else if(email.isEmpty()) {
            etUserEmail.setError("Please enter your email");
            etUserEmail.requestFocus();
        } else if (Password.isEmpty()){
            etUserPassword.setError("Please enter your password");
            etUserPassword.requestFocus();
        } else {
            result = true;
        }
        return result;

    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        //sendUserData();
                        Toast.makeText(RegistrationActivity.this,"Registration Successful, Verification mail sent! ",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

                    } else {
                        Toast.makeText(RegistrationActivity.this,"Verification has not been sent!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        Log.d("user id is: ", myRef.getKey());
        UserProfile userProfile = new UserProfile(name);
        myRef.setValue(userProfile);
    }

}