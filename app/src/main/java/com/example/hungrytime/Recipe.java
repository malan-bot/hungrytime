package com.example.hungrytime;


import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Recipe {
    private String name;
    private String imageUrl;
    private HashMap<String, ArrayList> ingredientsHashMap;
    private ArrayList<Ingredient> ingredients;
    private long likeCount;
    private ArrayList<String> tags;

    public Recipe(String name,
                  String imageUrl,
                  HashMap<String,ArrayList> ingredients,
                  long likeCount,
                  ArrayList<String> tags){

        this.name= name;
        this.imageUrl = imageUrl;
        this.ingredientsHashMap = ingredients;
        this.likeCount = likeCount;
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(HashMap<String, ArrayList> ingredients) {
        this.ingredientsHashMap = ingredients;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    //test constructor 1
    public Recipe(String description){
        this.name = name;
    }

    //test constructor 2
    public Recipe(String name, HashMap<String, ArrayList> ingredients){
        this.name = name;
        this.ingredientsHashMap = ingredients;
    }

    //test constructor 3: for hardcoding in Recipes w/ ingredients
    public Recipe(String name, ArrayList<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public Recipe(String name, String imageUrl, ArrayList<Ingredient> ingredients, long likeCount, ArrayList<String> tags) {

        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.likeCount = likeCount;
        this.tags = tags;
    }



    public String getDescription() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //For each ingredient in the ingredients HashMap, return an ArrayList of Ingredient Objects (stores the metric
    //retrieved from the db, and the String = the qty of the ingredients.

    //note: switch(metric)
    //          case cup: don't parseInt
    public HashMap<String, ArrayList> getIngredientsHashMap(){
        return ingredientsHashMap;
    };

    public ArrayList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public ArrayList<String> getTags() {
        return tags;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "description='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredients=" + ingredients +
                ", likeCount=" + likeCount +
                ", tags=" + tags +
                '}';
    }
}
