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


        Schedule schedule = initShopListData();
        ShoppingList shopList = new ShoppingList(schedule);
        Log.d("shopList", shopList.getShoppingList().toString());
        Log.d("itemList", shopList.getShoppingItems().toString());

        for(Item item : shopList.getShoppingItems()){
            ArrayList<String> recipes = item.getRecipes();
            String out = "";
            for(String string : recipes) {
                out += string + "\n";
            }
            Log.d("item", item.getItem() + "\n" + out);
            Log.d(" ", "-");

        }


        //*Testing ShopList
       // Log.d("MAIN: schedule: ", schedule.getScheduleInDays().toString());
        //ShoppingList testList = new ShoppingList(test);
        //testList.getShoppingList();
        //*/

        /*to Test Malan's Activity.. please run the following block of code:
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


    public Recipe createRecipe(String name, Ingredient... ingredients){
        ArrayList<Ingredient> ingredientsArray = new ArrayList<Ingredient>();
        for(Ingredient i : ingredients){
            ingredientsArray.add(new Ingredient(i.getIngredient(), i.getQty(), i.getMetric()));
        }
        Recipe recipe = new Recipe(name, ingredientsArray);
        return recipe;
    }


    /**
     * Kale Smoothie, Carrot&Orange smoothie, C,C,Pineapple Juice, Cherry Smoothie, Fennel&Blueberr J,
     * HdewCucuLime, KFruit smooth, Lemonwa, MangoBS, PeachMelba, StClem, StrawbeSmo
     *
     */

    public Schedule initShopListData(){

        //Hardcoded Ingredients
        Ingredient lime = new Ingredient("Lime", "x");
        Ingredient kale = new Ingredient("kale", "cup");
        Ingredient avocado = new Ingredient("avocado", "x");
        Ingredient pineapple = new Ingredient("pineapple", "x");
        Ingredient ginger = new Ingredient("ginger", "g");
        Ingredient cashew = new Ingredient("cashew", "tbsp");
        Ingredient banana = new Ingredient("banana", "x");
        Ingredient carrots = new Ingredient("carrot", "x");
        Ingredient almondMilk = new Ingredient("almond milk", "mL");
        Ingredient almondButter = new Ingredient("almond butter", "tbsp");
        Ingredient prunes = new Ingredient("prunes", "x");
        Ingredient strawberries = new Ingredient("strawberries", "x");
        Ingredient orangeJuice = new Ingredient("Orange Juice", "mL");
        Ingredient lemonJuice = new Ingredient("Lemon Juice", "mL");
        Ingredient orange = new Ingredient("Orange", "x");
        Ingredient lemon = new Ingredient("Lemon", "x");
        Ingredient lemonBitter = new Ingredient("Lemon (Bitter)", "mL");
        Ingredient peachHalves = new Ingredient("Peaches Halves", "g");
        Ingredient fraspberries = new Ingredient("Frozen raspberries", "g");
        Ingredient custard = new Ingredient("Fresh Custard", "mL");
        Ingredient mango = new Ingredient("Mango", "x");
        Ingredient ice  = new Ingredient("Ice", "(optional)");
        Ingredient water = new Ingredient("Water", "cup");
        Ingredient kiwifruit = new Ingredient("kiwifruit", "x");
        Ingredient pineappleJuice= new Ingredient("Pineapple Juice", "mL");
        Ingredient honeyDewMelon = new Ingredient("HoneyDew Melon", "x");
        Ingredient cucumber = new Ingredient("Cucumber", "x");
        Ingredient fennelBulb = new Ingredient("Fennel Bulb", "x");
        Ingredient apples = new Ingredient("Apples", "x");
        Ingredient blueberries = new Ingredient("Blueberries", "g");
        Ingredient cherries = new Ingredient("cherries", "g");
        Ingredient natYogurt = new Ingredient("Natural Yogurt", "g");
        Ingredient vanillaExtract = new Ingredient("Vanilla (Extract)", "tsp");
        Ingredient clementines = new Ingredient("Clementine", "x");
        Ingredient Ginger = new Ingredient("Lime", "x");
        Ingredient oats = new Ingredient("oats", "g");

        /**
         * Kale Smoothie, Carrot&Orange smoothie, C,C,Pineapple Juice, Cherry Smoothie, Fennel&Blueberr J,
         * HdewCucuLime, KFruit smooth, Lemonwa, MangoBS, PeachMelba, StClem, StrawbeSmo
         */
        //Hardcoded Recipes
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
        Log.d("schedule ", (schedule.getScheduleInDays()).toString());
        return schedule;
    }






















    //Helper function to easily create ingredient arrays
    public HashMap<String, ArrayList> createIngredient(String name, String metric, String qty) {
        HashMap<String, ArrayList> ingredient = new HashMap<String, ArrayList>();
        ingredient.put(name, new ArrayList<>(Arrays.asList(metric, qty)));
        return ingredient;

    }

    private void initRecyclerView(ArrayList<Day> schedule) {
        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this);
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

