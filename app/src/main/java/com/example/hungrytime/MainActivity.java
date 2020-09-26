package com.example.hungrytime;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "Recipes";

    public String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*      RECIPE SEARCH DEMO

        RecipeView recipeView = new RecipeView((RecyclerView) findViewById(R.id.recipeRecView), this);
        recipeView.readRecipesByTag(search);
        */

    /*      HOw to move from one intent to another??
            New intent = new Intent(search, ResultsActivity.class);
            //change the search field
            context.startActivity(intent);
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

        Log.d(TAG, shoplist.getShoppingList().toString());

        Log.d(TAG, schedule.getScheduleArray().toString());

        //Testing Juwons schedule parser
        for(int i = 0; i < 6; i++) {
            Log.d(TAG, getDay(schedule.getScheduleArray(), i));
            Log.d(TAG, getRecipes(schedule.getScheduleArray(),i ));
            Log.d(TAG, "-");
        }
        */

    }


    public String getDay(ArrayList<HashMap<String, ArrayList<Recipe>>> scheduleArray, int position){
        ArrayList<HashMap<String, ArrayList<Recipe>>> schedule = scheduleArray;

        String day = "";
        for(String key : schedule.get(position).keySet()){
            day = key;
        }
        return day + ": ";
    }

    public String getRecipes(ArrayList<HashMap<String, ArrayList<Recipe>>> scheduleArray, int position){
        String recipesString = "";
        for(String key : scheduleArray.get(position).keySet()){
            ArrayList<Recipe> recipes = scheduleArray.get(position).get(key);
            for(Recipe recipe : recipes){
                recipesString += recipe.getDescription() + " ";
            }
        }
        return recipesString;
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

