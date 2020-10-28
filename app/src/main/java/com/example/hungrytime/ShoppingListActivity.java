package com.example.hungrytime;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ScheduleAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShoppingListAdapter shopListAdapter = new ShoppingListAdapter(this);
        ShoppingList shopList = initShopListData();
        shopListAdapter.setShoppingList(shopList);
        recyclerView.setAdapter(shopListAdapter);
        getIntent();
    }

    public Recipe createRecipe (String name, Ingredient... ingredients){
        ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
        for(Ingredient i : ingredients){
            ingredientArray.add(new Ingredient(i.getIngredient(), i.getQty(), i.getMetric()));
        }
        Recipe recipe = new Recipe(name, ingredientArray);
        return recipe;
    }

    public ShoppingList initShopListData(){

        Ingredient lime = new Ingredient("Lime", "X");
        Ingredient kale = new Ingredient("kale", "cup");
        Ingredient avocado = new Ingredient("Avocado", "X");
        Ingredient pineapple = new Ingredient("Pineapple", "X");
        Ingredient ginger = new Ingredient("Ginger", "g");
        Ingredient cashew = new Ingredient("Cashew", "tbsp");
        Ingredient banana = new Ingredient("Banana", "X");
        Ingredient carrots = new Ingredient("Carrots", "X");
        Ingredient almondMilk = new Ingredient("Almond Milk", "mL");
        Ingredient almondButter = new Ingredient("Almond Butter", "tbsp");
        Ingredient prunes = new Ingredient("Prunes", "X");
        Ingredient strawberries = new Ingredient("Strawberries", "X");
        Ingredient orangeJuice = new Ingredient("Orange Juice", "mL");
        Ingredient lemonJuice = new Ingredient("Lemon Juice", "mL");
        Ingredient orange = new Ingredient("Orange", "X");
        Ingredient lemon = new Ingredient("Lemon", "X");
        Ingredient lemonBitter = new Ingredient("Lemon Bitter", "mL");
        Ingredient peachHalves = new Ingredient("Peach Halves", "g");
        Ingredient fraspberries = new Ingredient("Frozen Raspberries", "g");
        Ingredient custard = new Ingredient("Fresh Custard", "mL");
        Ingredient mango = new Ingredient("Mango", "X");
        Ingredient ice = new Ingredient("Ice", "(optional)");
        Ingredient water = new Ingredient("Water", "cup");
        Ingredient kiwifruit = new Ingredient("Kiwifruit", "X");
        Ingredient pineappleJuice = new Ingredient("Pineapple Juice", "mL");
        Ingredient honeyDewMelon = new Ingredient("Honeydew Melon", "X");
        Ingredient cucumber = new Ingredient("Cucumber", "X");
        Ingredient fennelBulb = new Ingredient("Fennel Bulb", "X");
        Ingredient apples = new Ingredient("Apples", "g");
        Ingredient blueberries = new Ingredient("Blueberries", "g");
        Ingredient cherries = new Ingredient("Cherries", "g");
        Ingredient natYogurt = new Ingredient("Natural Yogurt", "tsp");
        Ingredient vanillaExtract = new Ingredient("Vanilla (Extract)", "X");
        Ingredient clementines = new Ingredient("Clementine", "X");
        Ingredient oats = new Ingredient("Oats", "g");


        Recipe r1 = createRecipe("Carrot, Clementine & Pineapple Juice", pineapple.setQty("1/2"), ginger.setQty("14"), clementines.setQty("2"));
        Recipe r2 = createRecipe("Kale Smoothie", kale.setQty("2"), avocado.setQty("1/2"), lime.setQty("1/2"), pineapple.setQty("1"), ginger.setQty("28"), cashew.setQty("1"), banana.setQty("1"));
        Recipe r3 = createRecipe("Carrot & Orange Smoothie", carrots.setQty("2"), orange.setQty("2"), ginger.setQty("28"), oats.setQty("2"), ice.setQty("100"));
        Recipe r4 = createRecipe("Cherry Smoothie", cherries.setQty("300"), natYogurt.setQty("150"), banana.setQty("1"), vanillaExtract.setQty("1/2"));
        Recipe r5 = createRecipe("Fennel, Blueberry & Apple Juice", fennelBulb.setQty("1/2"), apples.setQty("1"), blueberries.setQty("1/2"), lemonJuice.setQty("1"));
        Recipe r6 = createRecipe("Honeydew Melon, Cucumber & Lime Juice", honeyDewMelon.setQty("1/4"), cucumber.setQty("1/2"), lime.setQty("1"));
        Recipe r7 = createRecipe("Kiwifruit Smoothie", kiwifruit.setQty("3"), mango.setQty("1"), pineappleJuice.setQty("500"), banana.setQty("1"));
        Recipe r8 = createRecipe("Lemon Water", water.setQty("1"), lemon.setQty("1/4"));
        Recipe r9 = createRecipe("Mango & Banana Smoothie", mango.setQty("1"), banana.setQty("1"), orangeJuice.setQty("500"), ice.setQty(""));
        Recipe r10 = createRecipe("Peach Melba Smoothie", peachHalves.setQty("410"), fraspberries.setQty("100"), orangeJuice.setQty("100"), custard.setQty("150"));
        Recipe r11 = createRecipe("St Clement's Rise & Shine", orange.setQty("1"), lemon.setQty("1/2"), lemonBitter.setQty("300"));
        Recipe r12 = createRecipe("Strawberry Smoothie", strawberries.setQty("10"), banana.setQty("1"), orangeJuice.setQty("100"));


        ArrayList<Recipe> recipes = new ArrayList<Recipe>(Arrays.asList(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12));

        ArrayList<Recipe> monday = new ArrayList<Recipe>();
        ArrayList<Recipe> tuesday = new ArrayList<Recipe>();
        ArrayList<Recipe> wednesday = new ArrayList<Recipe>();
        ArrayList<Recipe> thursday= new ArrayList<Recipe>();
        ArrayList<Recipe> friday = new ArrayList<Recipe>();
        ArrayList<Recipe> saturday = new ArrayList<Recipe>();
        ArrayList<Recipe> sunday = new ArrayList<Recipe>();

        Schedule schedule = new Schedule();
        ArrayList<ArrayList<Recipe>> scheduleArray = new ArrayList<ArrayList<Recipe>>(Arrays.asList(monday, tuesday, wednesday, thursday, friday,saturday, sunday));

        int i = 0;
        for(Recipe recipe : recipes){
            scheduleArray.get(i).add(recipe);
            i = ++i % 7;
        }




        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for(String day : days){
            switch(day){
                case "Monday":
                    for(Recipe recipe : monday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                case "Tuesday":
                    for(Recipe recipe  : tuesday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                case "Wednesday":
                    for(Recipe recipe  : wednesday){
                        schedule.insertRecipe(day,recipe);
                    }
                    break;
                case "Thursday":
                    for(Recipe recipe  : thursday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                case "Friday":
                    for(Recipe recipe  : friday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                case "Saturday":
                    for(Recipe recipe  : saturday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                case "Sunday":
                    for(Recipe recipe  : sunday){
                        schedule.insertRecipe(day, recipe);
                    }
                    break;
                default:
                    break;
            }
        };
        //This is returning an ArrayList<
        ShoppingList shopList = new ShoppingList(schedule);
        shopList.getShoppingList();
        return shopList;

    }




}
