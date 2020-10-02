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

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this recycler view is used by both Juwon & Malan's activities.
        recyclerView = findViewById(R.id.recyclerView);


        //*to Test Malan's Activity.. please run the following block of code:
        //Some tags you may want to try: Alcohol, Juice, Smoothie, Pineapple, Banana, Coconut, Dairy Free.
        RecipeView recipeView = new RecipeView(recyclerView, this);
        recipeView.readRecipesByTag("Alcohol");
        //*/

       /*To test Juwons Activity.. Please run this code:
        Schedule schedule = initScheduleData();
        initRecyclerView(schedule.getScheduleInDays());
        //*/
    }

    //We've been using this commented block to load recipes into our DB.
    /*      ArrayList ing1 = new ArrayList();
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

    private void initRecyclerView(ArrayList<Day> schedule) {
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(schedule);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(scheduleAdapter);
    }

    public Schedule initScheduleData(){
        Schedule schedule = new Schedule();

        String[] monday = new String[]{"A bowl of cereal", "Teriyaki salmon don", "Butter chicken on Rice"};
        String[] tuesday = new String[]{"Strawberry and banana smoothie", "BBQ pork on rice", "Pasta with Tomatoes", "Homemade energy bar"};
        String[] wednesday = new String[]{""};
        String[] thursday = new String[]{""};
        String[] friday = new String[]{"Kiwifruit on cereal", "Pelmeni", "Shakshuka"};
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



}

