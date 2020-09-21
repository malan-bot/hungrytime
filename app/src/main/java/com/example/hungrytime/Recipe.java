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


    /*
            Schedule:  HashMap<Date, ArrayList<Recipe>>

                                        [ {description, ingredients, imageUrl, likeCount, tags}, { desc..} ]

                                        for {Recipe recipe : recipes){
                                            new arraylist<Ingredient, metric, qty>
                                            HashMap<String, Arraylist> = .getIngredients()
                                            for( each set : hashmap){
                                                IF{
                                                     arrayList.contains(set.get(Item))
                                                     add(qty)
                                                     }
                                                else{
                                                    arrayList.add(new ingredient, qty);
                                                }
                                           }

                                           loadIngredientImages(){
                                                for each item in shopping:
                                                    doc = ingredientscollection = .get(









     */



/*
        Important: toObject will create a class from the fetched document so long as the attributes/fields make sense..
 */
    public Recipe(){
        //no args: this is for .toObject <FireStore method>
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
