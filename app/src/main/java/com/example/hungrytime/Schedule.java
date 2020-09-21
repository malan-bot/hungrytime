package com.example.hungrytime;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {
    private String scheduleId;
    private HashMap<String, HashMap<String, Recipe>> schedule;

    //We use a nested HashMap in order to ensure that only 1 of each recipe can be added to any given day.
    //We can enforce this with HashMaps as they require their keys to be unique, in our case keys = recipe names or ID.
    public Schedule() {
        this.schedule = new HashMap<String, HashMap<String,Recipe>>();
        this.schedule.put("Monday", new HashMap<String,Recipe>());
        this.schedule.put("Tuesday", new HashMap<String,Recipe>());
        this.schedule.put("Wednesday", new HashMap<String,Recipe>());
        this.schedule.put("Thursday", new HashMap<String,Recipe>());
        this.schedule.put("Friday", new HashMap<String,Recipe>());
        this.schedule.put("Saturday", new HashMap<String,Recipe>());
        this.schedule.put("Sunday", new HashMap<String,Recipe>());
    }

    //You can add only 1 of each recipe into any given day of the week.
    public void insertRecipe(String day, Recipe recipe) {
        if (schedule.containsKey(day)) {
            if (!schedule.get(day).containsKey(recipe.getDescription())) {
                schedule.get(day).put(recipe.getDescription(), recipe);
            }
            else{
                Log.d("Scheduling err: ", "Recipe has already been scheduled for " + day);
            }
        } else {
                Log.d("Scheduling err: ", day + " is not a valid");
        }
    }

    public HashMap<String, HashMap<String, Recipe>> getSchedule(){
        return this.schedule;
    }
    
}
