package com.example.hungrytime;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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


/*      RECIPE SEARCH DEMO

         RecipeView recipeView = new RecipeView((RecyclerView) findViewById(R.id.recipeRecView), this);

        recipeView.readRecipesByTag("Alcohol", new RecipeView.FirebaseCallback() {
            @Override
            public void onCallback(String list) {
                Log.d(TAG, list.toString());
            }
        });
        */

        /*  SCHEDULE & SHOPPING LIST DEMO

        Schedule schedule = new Schedule();
        Recipe recipe1 = new Recipe("Recipe1", createIngredient("Ingredient1", "g", "1"));
        Recipe recipe2 = new Recipe("Recipe2", createIngredient("Ingredient2", "g", "2"));
        recipe1.getIngredients().put("Ingredient2", new ArrayList<>(Arrays.asList("kg", "5")));

        schedule.insertRecipe("Monday", recipe1);
        schedule.insertRecipe("Monday", recipe2);
        schedule.insertRecipe("Monday", recipe1);
        schedule.insertRecipe("Tuesday", recipe1);
        Log.d(TAG, schedule.getSchedule().toString());

        ShoppingList shoplist = new ShoppingList(schedule);
        //Log.d(TAG, shoplist.getSchedule().toString());

        shoplist.processSchedule();
        Log.d(TAG, shoplist.getShoppingList().toString());
        */

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

    //Helper function to easily create ingredient arrays
    public HashMap<String, ArrayList> createIngredient(String name, String metric, String qty) {
        HashMap<String, ArrayList> ingredient = new HashMap<String, ArrayList>();
        ingredient.put(name, new ArrayList<>(Arrays.asList(metric, qty)));
        return ingredient;

    }
}

