package com.example.hungrytime;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeRecView;
    //Recipes are loaded into here
    public static ArrayList<Recipe> recipes;
    //TAG's used for Logcat logs
    public final String TAG = "Recipes";
    public static RecipeViewAdapter adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference recipeRef = db.collection("Recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRecView = findViewById(R.id.recipeRecView);
        adapter = new RecipeViewAdapter(this);
        recipes = new ArrayList<Recipe>();

        //example: creating a new recipe & loading it into the Recipes collection
        ArrayList<HashMap<String, Object>> newRecipe = new ArrayList<HashMap<String, Object>>();
        ArrayList<String> newTags = new ArrayList<String>(Arrays.asList("Lemon", "Drink", "No Sugar"));
        ArrayList ingredients = new ArrayList();
        ingredients.add(c("Water", "cup", "1"));
        ingredients.add(c("Lemon", "wedge", "1"));
        loadNewRecipe("Lemon water", "https://", ingredients, 0, newTags);

        //Searching through the Recipes collection. First param = item = Recipe tag to search for, e.g: alcohol
        readRecipesByTag("Alcohol", new FirebaseCallback() {
            @Override
            public void onCallback(String list) {
                Log.d(TAG, list.toString());
            }
        });
    }

    //Helper function to easily create ingredient arrays
    private String[] c(String name, String metric, String qty) {
        return new String[]{name, metric, qty};
    }

    //Loads a new Recipe into our Firestore Recipes Collection
    private void loadNewRecipe(String name, String imageUrl, ArrayList<String[]> ingredients, long likeCount, ArrayList<String> tags) {
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


    //Fetches recipes from the Recipes collection by tag
    private void readRecipesByTag (String item,final FirebaseCallback firebaseCallback){
        recipeRef.whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) { if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d(TAG, document.getId() + " => " + document.getData());

                    //fetched recipe  stored here
                    Map<String, Object> doc = document.getData();
                    //fetched ingredients stored here
                    ArrayList<HashMap<String, ArrayList>> fetchedIngredients = (ArrayList<HashMap<String, ArrayList>>) doc.get("Ingredients");

                    //ingredients stored here
                    HashMap<String, ArrayList> ingredients = new HashMap<String, ArrayList>();
                    //Parses through the fetched ingredients arraylist
                    for (HashMap<String, ArrayList> set : fetchedIngredients) {
                        Iterator entries = set.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry entry = (Map.Entry) entries.next();
                            String item = (String) entry.getValue();
                            entry = (Map.Entry) entries.next();
                            ArrayList qty = (ArrayList) entry.getValue();
                            ingredients.put(item, qty);
                        }
                    }
                    Log.d(TAG, "ingredients hashmap: " + ingredients.toString());

                    Recipe recipe = new Recipe((String) doc.get("Description"), (String) doc.get("Image"), ingredients, (long) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
                    recipes.add(recipe);
                }
                firebaseCallback.onCallback(recipes.toString());
            }
            MainActivity.adapter.setRecipes(recipes);
            recipeRecView.setAdapter(adapter);
            recipeRecView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
        });
    }

    //I believe this is necessary? Part of the solution I found for dealing with asynchronous behavior.
    private interface FirebaseCallback{
        void onCallback(String recipe);
    }
}
