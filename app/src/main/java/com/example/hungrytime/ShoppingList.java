package com.example.hungrytime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ShoppingList {
    private HashMap<String, HashMap<String, String>> ingredientRecipesMap;
    private Schedule schedule;
    private final String TAG = "ShoppingList:";

    public ShoppingList(Schedule schedule) {
        this.schedule = schedule;
    }

    /*      Reminder: It may be worth returning the 'day' for the recipe. A user schedules a recipe more than once a week.
            This may go into the into 1. the recipesQuantitiesMap -> the ingredientsRecipesMap
            We could append it to the beginning of a recipe name, so:   <"MON: Recipe1", "qty">      */

    private void processSchedule() {
        //processSchedule() is called when ingredientRecipesMap is null. We start off by creating an empty HashMap to work with
        this.ingredientRecipesMap = new HashMap<String, HashMap<String, String>>();

        //Used for processing the schedule: As this function iterates through each and every ingredient, if there is an ingredient that doesn't exist in this ArrayList,
        //then we know to create a new key in the ingredientRecipesMap for the ingredient.
        ArrayList<String> ingredientsList = new ArrayList<String>();

        //Now we want to iterate through each day of the week
        Iterator days = this.schedule.getSchedule().entrySet().iterator();
        while (days.hasNext()) {
            ArrayList<Recipe> scheduledRecipes = new ArrayList<Recipe>();
            Map.Entry day = (Map.Entry) days.next();

            //Now iterate through each Recipe in the Recipes HashMap which is found in day.getValue(). Retrieve all recipes & store it in the scheduledRecipes ArrayList.
            Iterator recipe = ((HashMap<String, Recipe>) day.getValue()).entrySet().iterator();
            while (recipe.hasNext()) {
                Map.Entry item = (Map.Entry) recipe.next();
                scheduledRecipes.add((Recipe) item.getValue());
            }
            //Now for each recipe in scheduledRecipes...
            for (Recipe r : scheduledRecipes) {
                HashMap<String, ArrayList> ingredients = r.getIngredients();
                //Iterate through every ingredient...
                Iterator ingredient = ((HashMap<String, ArrayList>)ingredients).entrySet().iterator();
                while (ingredient.hasNext()) {
                    Map.Entry entry = (Map.Entry) ingredient.next();
                    String item = (String) entry.getKey();
                    final ArrayList qty = (ArrayList)entry.getValue();

                    //if ingredient is not listed in the IngredientsList, then add the ingredient to the Array.
                    //We also want to add the ingredients + qty to the ingredientRecipesMap. This will be processed later to fill the recipeQuantities Map which outlines <Ingredient <Recipe, Qty>>.
                    final String recipeholder = r.getDescription();
                    if (!ingredientsList.contains(item)) {
                        ingredientsList.add((String) entry.getKey());
                        ingredientRecipesMap.put(item, new HashMap<String, String>() {{
                            put(recipeholder, (String) qty.get(1) +  (String) qty.get(0));
                        }});
                    } else {
                        ingredientRecipesMap.get(item).put(recipeholder, (String) qty.get(1) + (String) qty.get(0));
                    }
                }

            }
        }
    }

    public HashMap<String, HashMap<String, String>> getShoppingList(){
        if(this.ingredientRecipesMap == null){
            processSchedule();
            return this.ingredientRecipesMap;
        }
        return ingredientRecipesMap;
    }


    public Schedule getSchedule(){
        return this.schedule;
    }
}










