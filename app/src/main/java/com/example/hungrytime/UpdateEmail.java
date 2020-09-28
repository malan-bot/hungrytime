package com.example.hungrytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmail extends AppCompatActivity {
    private TextView tvEmail;
    private EditText etNewEmail;
    private Button btnUpdateEmail;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        tvEmail = findViewById(R.id.tvEmail);
        etNewEmail = findViewById(R.id.etNewEmail);
        btnUpdateEmail = findViewById(R.id.btnUpdateEmail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = etNewEmail.getText().toString();
                firebaseUser.updateEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            firebaseUser.sendEmailVerification();
                            Toast.makeText(UpdateEmail.this,"Email Changed, Verification mail sent! ",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(UpdateEmail.this,ProfileActivity.class));
                        }else {
                            Toast.makeText(UpdateEmail.this,"Email update failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}