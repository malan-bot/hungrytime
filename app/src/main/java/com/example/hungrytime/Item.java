package com.example.hungrytime;

import java.util.ArrayList;

public class Item {
    private String item;
    private ArrayList<String> recipes;
    private String quantity;
    private boolean expanded;

    public Item(String item, ArrayList<String> recipes, String quantity) {
        this.item = item;
        this.recipes = recipes;
        this.quantity = quantity;
        this.expanded = false;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ArrayList<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<String> recipes) {
        this.recipes = recipes;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                ", recipes=" + recipes + '\'' +
                ", quantity='" + quantity + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
