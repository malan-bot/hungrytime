package com.example.hungrytime;


import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String description;
    private String imageUrl;
    private HashMap<String, ArrayList> ingredients;
    private long likeCount;
    private ArrayList<String> tags;

    public Recipe(String description,
                  String imageUrl,
                  HashMap<String,ArrayList> ingredients,
                  long likeCount,
                  ArrayList<String> tags){

        this.description = description;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.likeCount = likeCount;
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(HashMap<String, ArrayList> ingredients) {
        this.ingredients = ingredients;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    //test constructor 1
    public Recipe(String description){
        this.description = description;
    }

    //test constructor 2
    public Recipe(String description, HashMap<String, ArrayList> ingredients){
        this.description = description;
        this.ingredients = ingredients;
    }


    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //For each ingredient in the ingredients HashMap, return an ArrayList of Ingredient Objects (stores the metric
    //retrieved from the db, and the String = the qty of the ingredients.

    //note: switch(metric)
    //          case cup: don't parseInt
    public HashMap<String, ArrayList> getIngredients(){
        return ingredients;
    };


    public long getLikeCount() {
        return likeCount;
    }

    public ArrayList<String> getTags() {
        return tags;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ingredients=" + ingredients +
                ", likeCount=" + likeCount +
                ", tags=" + tags +
                '}';
    }
}
