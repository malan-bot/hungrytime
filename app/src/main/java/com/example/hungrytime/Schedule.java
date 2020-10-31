package com.example.hungrytime;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Schedule {
    //ScheduleID is for when we manage to refactor Mohammed's code to work with Firestore. We'll then have a Users collection which will help us keep track of which scheduleID belongs to which user.
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

    //Based on what Juwon's shown me, this should work.
    public ArrayList<Day> getScheduleInDays() {
        String[] daysInAWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayList<Day> daysArray = new ArrayList<Day>();

        for (int i = 0; i < 7; i++) {
            daysArray.add(new Day(daysInAWeek[i]));
        }

        Iterator days = this.schedule.entrySet().iterator();
        while (days.hasNext()) {
            final Map.Entry day = (Map.Entry) days.next();

            ArrayList<Recipe> scheduledRecipes = new ArrayList<Recipe>();
            Iterator recipes = ((HashMap<String, Recipe>) day.getValue()).entrySet().iterator();
            while (recipes.hasNext()) {
                Map.Entry recipe = (Map.Entry) recipes.next();
                scheduledRecipes.add((Recipe) recipe.getValue());
            }

            int daysArrayIndex = getDayIndex((String) day.getKey());
            daysArray.get(getDayIndex((String)day.getKey())).setRecipes(scheduledRecipes);
        }

        return daysArray;
    }

    public int getDayIndex(String day) {
        switch (day) {
            case "Monday":
                return 0;
            case "Tuesday":
                return 1;
            case "Wednesday":
                return 2;
            case "Thursday":
                return 3;
            case "Friday":
                return 4;
            case "Saturday":
                return 5;
            case "Sunday":
                return 6;
            default:
                Log.d("Schedule: ", "getDayIndex error");
                return 8;
        }

    }





}
