package com.example.hungrytime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class LoadingRecipes extends AppCompatActivity {

    TextView mTitleTv;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        mRecyclerView = findViewById(R.id.recyclerView3);

        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.title);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("iTitle");
        actionBar.setTitle(mTitle);
        mTitle = mTitle.equals("Alcoholic Drinks") ? "Alcohol" : mTitle;



        RecipeView recipeView = new RecipeView(mRecyclerView, this);
        recipeView.readRecipesByTag(mTitle);


    }
}