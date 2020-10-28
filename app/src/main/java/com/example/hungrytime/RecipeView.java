package com.example.hungrytime;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RecipeView {
    RecyclerView recipeRecyclerView;
    public static ArrayList<Recipe> recipes;
    public final String TAG = "RecipesView: ";
    public static RecipeViewAdapter adapter;
    private Context context;
    private static Schedule schedule;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference recipeRef = db.collection("Recipes");

    public RecipeView(RecyclerView view, Context context) {
        this.context = context;
        recipeRecyclerView = view;
        adapter = new RecipeViewAdapter(context);
        recipes = new ArrayList<Recipe>();
    }

    //Use this when you just want access to the recipes
    public RecipeView(){
        recipes = new ArrayList<Recipe>();
    }

    //creates tag arraylist
    public ArrayList<String> createTags(String... tags){
        ArrayList<String> tagArray = new ArrayList<String>();
        for(String tag : tags){
            tagArray.add(tag);
        }
        return tagArray;
    }

    //Helper function to easily create ingredient arrays
    public String[] c(String name, String metric, String qty) {
        return new String[]{name, metric, qty};
    }

    //Loads a new Recipe into our Firestore Recipes Collection
    public void loadNewRecipe(String name, String imageUrl, ArrayList<String[]> ingredients, long likeCount, ArrayList<String> tags) {
        ArrayList<HashMap<String, Object>> i = new ArrayList<HashMap<String, Object>>();

        int pos = 0;
        for (String[] ingredient : ingredients) {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            temp.put("Item", ingredients.get(pos)[0]);
            temp.put("Quantity", new ArrayList<>(Arrays.asList(ingredients.get(pos)[1], ingredients.get(pos)[2])));
            i.add(temp);
            pos++;
        }
        Map<String, Object> recipe =  new HashMap<String, Object>();
        recipe.put("Description", name);
        recipe.put("Image", imageUrl);
        recipe.put("Ingredients", i);
        recipe.put("likeCount", likeCount);
        recipe.put("tags", tags);
        recipeRef.document(name).set(recipe);
    }


    public Schedule getSchedule(){
        return getRecipesByTag("Alcohol", new FirestoreCallback(){
            @Override
            public void onCallback(String string){}
            });
    }

    public Schedule getRecipesByTag(String tag, FirestoreCallback firestoreCallback){
        schedule = new Schedule();
        final FirestoreCallback fsCallback = firestoreCallback;
        recipeRef.whereArrayContains("tags", tag).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());

                        //fetched recipe  stored here
                        Map<String, Object> doc = document.getData();
                        //fetched ingredients stored here
                        ArrayList<HashMap<String, ArrayList>> fetchedIngredients = (ArrayList<HashMap<String, ArrayList>>) doc.get("Ingredients");

                        //ingredients stored here
                        //HashMap<String, ArrayList> ingredients = new HashMap<String, ArrayList>();
                        ArrayList<Ingredient> ingredientsTest = new ArrayList<Ingredient>();
                        //Parses through the fetched ingredients arraylist
                        for (HashMap<String, ArrayList> set : fetchedIngredients) {
                            Iterator entries = set.entrySet().iterator();
                            while (entries.hasNext()) {
                                Map.Entry entry = (Map.Entry) entries.next();
                                String item = (String) entry.getValue();
                                entry = (Map.Entry) entries.next();
                                ArrayList qty = (ArrayList) entry.getValue();
                                //ingredients.put(item, qty);
                                ingredientsTest.add(new Ingredient(item, (String)qty.get(0), (String)qty.get(1)));
                            }
                        }
                        Recipe recipe = new Recipe((String) doc.get("Description"), (String) doc.get("Image"), ingredientsTest, (long) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
                        recipes.add(recipe);
                    }

                ArrayList<Recipe> monday = new ArrayList<Recipe>();
                ArrayList<Recipe> tuesday = new ArrayList<Recipe>();
                ArrayList<Recipe> wednesday = new ArrayList<Recipe>();
                ArrayList<Recipe> thursday= new ArrayList<Recipe>();
                ArrayList<Recipe> friday = new ArrayList<Recipe>();
                ArrayList<Recipe> saturday = new ArrayList<Recipe>();
                ArrayList<Recipe> sunday = new ArrayList<Recipe>();

                Log.d("Monday", monday.toString());

                ArrayList<ArrayList<Recipe>> testSchedule = new ArrayList<ArrayList<Recipe>>(Arrays.asList(monday, tuesday, wednesday, thursday, friday,saturday, sunday));

                for(Recipe recipe : recipes){
                    int i = 0;
                    testSchedule.get(i).add(recipe);
                    i = ++i % 6;
                }
                Log.d("testSchedule:", testSchedule.toString());
                Log.d("Monday", monday.toString());
                for(Recipe r : monday){
                    schedule.insertRecipe("monday", r);
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
                    };fsCallback.onCallback("string");
                }});
                return schedule;
    }


    public void readRecipesByTag(String item){

        recipeRef.whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) { if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    //Log.d(TAG, document.getId() + " => " + document.getData());

                    //fetched recipe  stored here
                    Map<String, Object> doc = document.getData();
                    //fetched ingredients stored here
                    ArrayList<HashMap<String, ArrayList>> fetchedIngredients = (ArrayList<HashMap<String, ArrayList>>) doc.get("Ingredients");

                    //ingredients stored here
                    //HashMap<String, ArrayList> ingredients = new HashMap<String, ArrayList>();
                    ArrayList<Ingredient> ingredientsTest = new ArrayList<Ingredient>();
                    //Parses through the fetched ingredients arraylist
                    for (HashMap<String, ArrayList> set : fetchedIngredients) {
                        Iterator entries = set.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry) entries.next();
                            String item = (String) entry.getValue();
                            entry = (Map.Entry) entries.next();
                            ArrayList qty = (ArrayList) entry.getValue();
                            //ingredients.put(item, qty);
                            ingredientsTest.add(new Ingredient(item, (String)qty.get(0), (String)qty.get(1)));
                        }
                    }
                    Recipe recipe = new Recipe((String) doc.get("Description"), (String) doc.get("Image"), ingredientsTest, (long) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
                    recipes.add(recipe);
                }
            }
                adapter.setRecipes(recipes);
                recipeRecyclerView.setAdapter(adapter);
                recipeRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            }
        });
    }

    private interface FirestoreCallback{
        void onCallback(String recipe);
    }




}


