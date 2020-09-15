package com.example.hungrytime;


import java.util.ArrayList;
import java.util.HashMap;

public class Recipe {
    private String description;
    private String imageUrl;
    private HashMap<String, String[]> ingredients;
    private int likeCount;
    private ArrayList<String> tags;

/*
        Important: toObject will create a class from the fetched document so long as the attributes/fields make sense..
 */

    public Recipe(){
        //no args: this is for .toObject <FireStore method>
    }

    //test 1
    public Recipe(String description, String imageUrl){
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setIngredients(HashMap<String, String[]> ingredients) {
        this.ingredients = ingredients;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Recipe(String description,
                  String imageUrl,
                  HashMap<String, String[]> ingredients,
                  int likeCount,
                  ArrayList<String> tags){

        this.description = description;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.likeCount = likeCount;
        this.tags = tags;
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
    public ArrayList<HashMap<Ingredient, String>> getIngredients(){

        return null;
    };


    public int getLikeCount() {
        return likeCount;
    }

    public ArrayList<String> getTags() {
        return tags;
    }


}
