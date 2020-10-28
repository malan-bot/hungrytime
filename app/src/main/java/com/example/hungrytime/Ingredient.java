package com.example.hungrytime;

public class Ingredient{
    private String ingredient;
    private String qty;
    private String metric;

    public Ingredient(String ingredient, String qty, String metric){
        this.ingredient = ingredient;
        this.qty = qty;
        this.metric = metric;
    }

    public Ingredient(String ingredient, String metric){
        this.ingredient=ingredient;
        this.metric=metric;
    }

    public Ingredient(String ingredient){
        this.ingredient=ingredient;
    }


    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQty() {
        return qty;
    }

    public Ingredient setQty(String qty) {
        this.qty = qty;
        return this;
    }

    public String getMetric() {
        return metric;
    }

    public String setMetric(String metric) {
        this.metric = metric;
        return this.metric;
    }

}
