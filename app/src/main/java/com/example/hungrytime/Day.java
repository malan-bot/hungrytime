package com.example.hungrytime;

import java.util.ArrayList;

public class Day {
    private String day;
    private ArrayList<Recipe> recipes;
    private boolean expanded;

    public Day(String day) {
        this.day = day;
        this.expanded = false;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "day='" + day + '\'' +
                ", recipe='" + recipes.toString() + '\'' +
                ", expanded=" + expanded +
                '}';
    }

}
