package com.example.hungrytime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    // ImageView ivSplash;
    TextView tvSplash;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvSplash = findViewById(R.id.tvSplash);
        tvSplash.animate().alpha(3000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intToMain = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intToMain);
                finish();
            }
        },3000);
    }
}