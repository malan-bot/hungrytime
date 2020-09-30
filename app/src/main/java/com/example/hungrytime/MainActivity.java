package com.example.hungrytime;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "Recipes";

    public String search;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Schedule schedule = initScheduleData();
        initRecyclerView(schedule.getScheduleInDays());

    }

    private void initRecyclerView(ArrayList<Day> schedule) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scheduleAdapter);
    }

    public Schedule initScheduleData(){
        Schedule schedule = new Schedule();

        String[] monday = new String[]{"3/4 cup corn flakes, 1 banana, 1 cup milk in a bowl", "Teriyaki salmon don", "Butter chicken curry, 0.5 bowl of rice, vege"};
        String[] tuesday = new String[]{"1 cup strawberry and banana smoothie", "BBQ pork on rice", "Pasta with tomato sauce", "1 energy bar"};
        String[] wednesday = new String[]{""};
        String[] thursday = new String[]{""};
        String[] friday = new String[]{"1 bowl of cereal and kiwifruits", "Pelmeni", "Shakshuka with pita pocket"};
        String[] saturday = new String[]{""};
        String[] sunday = new String[]{""};

        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for(String day : days){
            switch(day){
                case "Monday":
                    for(String recipe : monday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Tuesday":
                    for(String recipe : tuesday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Wednesday":
                    for(String recipe : wednesday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Thursday":
                    for(String recipe : thursday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Friday":
                    for(String recipe : friday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Saturday":
                    for(String recipe : saturday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                case "Sunday":
                    for(String recipe : sunday){
                        schedule.insertRecipe(day, new Recipe(recipe));
                    }
                    break;
                default:
                    break;
            }
        } return schedule;
    }

    public String getDay(ArrayList<HashMap<String, ArrayList<Recipe>>> scheduleArray, int position){
        ArrayList<HashMap<String, ArrayList<Recipe>>> schedule = scheduleArray;

        String day = "";
        for(String key : schedule.get(position).keySet()){
            day = key;
        }
        return day + ": ";
    }

    public String getRecipes(ArrayList<HashMap<String, ArrayList<Recipe>>> scheduleArray, int position){
        String recipesString = "";
        for(String key : scheduleArray.get(position).keySet()){
            ArrayList<Recipe> recipes = scheduleArray.get(position).get(key);
            for(Recipe recipe : recipes){
                recipesString += recipe.getDescription() + " ";
            }
        }
        return recipesString;
    }



/*
        ArrayList ing1 = new ArrayList();
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        ing1.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing1, 0, recipeView.createTags());


        ArrayList ing2 = new ArrayList();
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        ing2.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing2, 0, recipeView.createTags());

        ArrayList ing3 = new ArrayList();
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        ing3.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing3, 0, recipeView.createTags());

        ArrayList ing4 = new ArrayList();
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        ing4.add(recipeView.c("", "", ""));
        recipeView.loadNewRecipe("", "", ing4, 0, recipeView.createTags());
*/

    //Helper function to easily create ingredient arrays
    public HashMap<String, ArrayList> createIngredient(String name, String metric, String qty) {
        HashMap<String, ArrayList> ingredient = new HashMap<String, ArrayList>();
        ingredient.put(name, new ArrayList<>(Arrays.asList(metric, qty)));
        return ingredient;

    }
}

