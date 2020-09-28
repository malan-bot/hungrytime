package com.example.hungrytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText etName, etPassword;
    private Button btnLogin;
    private TextView tvRegister, tvForgotPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etUserPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            startActivity(new Intent(LoginActivity.this,SecondActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etName.getText().toString();
                String pwd = etPassword.getText().toString();

                if (email.isEmpty()){
                    etName.setError("Please enter your email");
                    etName.requestFocus();
                } else if(pwd.isEmpty()){
                    etPassword.setError("Please enter your password");
                    etPassword.requestFocus();
                } else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    validate(etName.getText().toString(), etPassword.getText().toString());
                }else{
                    Toast.makeText(LoginActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();
                }



            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intSignup = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intSignup);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intToForgot = new Intent(LoginActivity.this,PasswordActivity.class);
                startActivity(intToForgot);
            }
        });
    }

    private void validate (String userName, String userPassword){
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    finish();
                    checkEmailVerification();
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this,"Login Failed, Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailRegistered = firebaseUser.isEmailVerified();

        if(emailRegistered){
            startActivity(new Intent(LoginActivity.this,SecondActivity.class));
        } else {
            Toast.makeText(LoginActivity.this,"Verify your email to login",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}


