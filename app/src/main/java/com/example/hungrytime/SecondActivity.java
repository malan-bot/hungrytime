package com.example.hungrytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intToMin = new Intent(SecondActivity.this,MainActivity.class);
//                startActivity(intToMin);
                Logout();
            }
        });
    }
    private void Logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intToMin = new Intent(SecondActivity.this,LoginActivity.class);
        startActivity(intToMin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnLogout:{
                Logout();
                break;
            }
            case R.id.profileMenu:{
                startActivity(new Intent(SecondActivity.this,ProfileActivity.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}