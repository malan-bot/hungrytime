package com.example.hungrytime;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeRecView;
    //Recipes are loaded into here
    public static ArrayList<Recipe> recipes;
    //TAG's used for Logcat logs
    public final String TAG = "Recipes";
    public static RecipeViewAdapter adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference recipeRef = db.collection("Recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeView recipeView = new RecipeView((RecyclerView) findViewById(R.id.recipeRecView), this);

        recipeView.readRecipesByTag("Juice", new RecipeView.FirebaseCallback() {
            @Override
            public void onCallback(String list) {
                Log.d(TAG, list.toString());
            }
        });
    }
}

/*
        ArrayList ing1 = new ArrayList();
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing1, 0, recipeView.createTags());


        ArrayList ing2 = new ArrayList();
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing2, 0, recipeView.createTags());

        ArrayList ing3 = new ArrayList();
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing3, 0, recipeView.createTags());

        ArrayList ing4 = new ArrayList();
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing4, 0, recipeView.createTags());
           */
