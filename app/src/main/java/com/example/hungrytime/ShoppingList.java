package com.example.hungrytime;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ShoppingList {
    private HashMap<String, HashMap<String, String>> ingredientsMap;
    private HashMap<String, String> metricsMap;
    private Schedule schedule;
    private final String TAG = "ShoppingList:";

    public ShoppingList(Schedule schedule) {
        this.schedule = schedule;
    }

    /*      Reminder: It may be worth returning the 'day' for the recipe. A user schedules a recipe more than once a week.
            This may go into the into 1. the recipesQuantitiesMap -> the ingredientsRecipesMap
            We could append it to the beginning of a recipe name, so:   <"MON: Recipe1", "qty">      */

    public void shopListParser() {
        this.ingredientsMap = new HashMap<String, HashMap<String, String>>();
        this.metricsMap = new HashMap<String, String>();
        ArrayList<String> existingIngredients = new ArrayList<String>();

        //Here is the first step to the extraction process. We want to set up an iterator which
        //will go through each day of the week, for each day of the week we want to set up an
        //empty array for the recipes scheduled for that day, which we'll populate in the next code block.
        Iterator dayIter = this.schedule.getSchedule().entrySet().iterator();
        while (dayIter.hasNext()) {
            ArrayList<Recipe> scheduledRecipes = new ArrayList<Recipe>();
            Map.Entry day = (Map.Entry) dayIter.next();

            //Now we're extracting each recipe from the schedule of recipes for the current day
            Iterator recipeIter = ((HashMap<String, Recipe>) day.getValue()).entrySet().iterator();
            while (recipeIter.hasNext()) {
                Map.Entry item = (Map.Entry) recipeIter.next();
                scheduledRecipes.add((Recipe) item.getValue());
            }
            //Now, extract all ingredients from our recipes
            for (Recipe r : scheduledRecipes) {
                ArrayList<Ingredient> ingredients = r.getIngredients();
                final String recipeHolder = r.getDescription();

                for (Ingredient ingredient : ingredients) {
                    final Ingredient i = ingredient;
                    final String qty;

                    if (i.getMetric().equalsIgnoreCase("(optional)")) {
                        qty = i.getQty().equals("") ? i.getMetric() : i.getQty() + "g";
                    } else {
                        qty = i.getMetric().equals("x") ? i.getMetric() + i.getQty() : i.getQty() + i.getMetric();
                    }

                    if (!existingIngredients.contains(i.getIngredient())) {
                        existingIngredients.add(i.getIngredient());
                        ingredientsMap.put(i.getIngredient(), new HashMap<String, String>() {{
                            put(recipeHolder, qty);
                        }});
                        metricsMap.put(i.getIngredient(), i.getMetric());
                    } else {
                        ingredientsMap.get(i.getIngredient()).put(recipeHolder, qty);
                    }
                }
            }
        }
    }


    public ArrayList<Item> getShoppingItems() {
        ArrayList<Item> itemsList = new ArrayList<Item>();

        Iterator ingredientIter = ingredientsMap.entrySet().iterator();
        while (ingredientIter.hasNext()) {
            String item;
            String metric = "";
            ArrayList<String> recipes = new ArrayList<String>();
            String totalQty = "";

            Map.Entry ingredient = (Map.Entry) ingredientIter.next();
            item = (String) ingredient.getKey();

            for (Map.Entry<String, String> key : metricsMap.entrySet()) {
                if (key.getKey().equals(item)) {
                    metric = key.getValue();
                }
            }
            Log.d("item + metric", item + " " + metric);

            Iterator recipeIter = ((HashMap<String, String>) ingredient.getValue()).entrySet().iterator();
            while (recipeIter.hasNext()) {
                Map.Entry recipe = (Map.Entry) recipeIter.next();
                String recipeName = (String) recipe.getKey();
                String recipeQty = (String) recipe.getValue();
                totalQty += recipeQty;

                recipes.add(String.format("%s %s", recipeName, recipeQty));
            }
            itemsList.add(new Item(item, recipes,  totalQty));
        }
        return itemsList;
    }


    public String addFraction(String a, int nom2, int denom2) {
        int denom1 = Integer.parseInt(a.substring(a.length() - 1));
        int comd = denom1 * denom2;
        int newNomAnswer = Integer.parseInt(a.substring(0,1)) * denom2 + nom2 * denom1;
        int gcd = gcdEuclidsAlgorithm(denom1, denom2);
        return (Integer.toString(newNomAnswer / gcd) + Integer.toString(gcd));
    }

    public int gcdEuclidsAlgorithm(int n1, int n2){
        if(n2 == 0){
            return n1;
        }
        return gcdEuclidsAlgorithm(n2, n1 % n2);
    }




    public HashMap<String, HashMap<String, String>> getShoppingList(){
        if(this.ingredientsMap == null){
                shopListParser();
            }
            return this.ingredientsMap;
    }

    public Schedule getSchedule(){
        return this.schedule;
    }


    //REDUNDANT CODE
    /*
    private void processSchedule() {
        //processSchedule() is called when ingredientRecipesMap is null. We start off by creating an empty HashMap to work with
        this.ingredientsMap = new HashMap<String, HashMap<String, String>>();

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
                HashMap<String, ArrayList> ingredients = r.getIngredientsHashMap();
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
                        ingredientsMap.put(item, new HashMap<String, String>() {{
                            put(recipeholder, (String) qty.get(1) +  (String) qty.get(0));
                        }});
                    } else {
                        ingredientsMap.get(item).put(recipeholder, (String) qty.get(1) + (String) qty.get(0));
                    }
                }

            }
        }
    }
    */

}











