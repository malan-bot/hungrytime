package com.example.hungrytime;

public class Ingredient {
    private String description;
    private String metric;
    private String imageUrl;

    public Ingredient(String description, String imageUrl){
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Ingredient(String description, String metric, String imageUrl){
        this.description = description;
        this.metric = metric;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return imageUrl;
    }

}
