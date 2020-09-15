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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeRecView;
    public static ArrayList<Recipe> recipes;
    public final String TAG = "Recipes";
    public static RecipeViewAdapter adapter;

    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Recipes/");
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference recipeRef = db.collection("Recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRecView = findViewById(R.id.recipeRecView);
        adapter = new RecipeViewAdapter(this);
        recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("recipe 1", "url"));

        readData("Mimosa", new FirebaseCallback() {
            @Override
            public void onCallback(String list) {
                Log.d(TAG, list.toString());
            }
        });




    }
        /*
        RecipeViewAdapter adapter = new RecipeViewAdapter(this);
        adapter.setRecipes(fetchRecipe("Mimosa"));
        recipeRecView.setAdapter(adapter);
        recipeRecView.setLayoutManager(new GridLayoutManager(this, 2));
*/
    public ArrayList<Recipe> fetchRecipe(String item) {
        db.collection("Recipes").whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Map<String, Object> doc = document.getData();
                            Recipe recipe = new Recipe((String)doc.get("Description"), (String)doc.get("Image")); //, (HashMap<String,String[]>)doc.get("Ingredients"), (int) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
                            Log.d(TAG, recipe.getDescription());
                            Log.d(TAG, recipe.getImageUrl());

                            recipes.add(recipe);
                            Log.d(TAG, Integer.toString(recipes.size()));
                    }
                    Log.d(TAG, "exited for loop");

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                Log.d(TAG, "exited if clause");
            }
        });
        return recipes;
    }


    private void readData(String item, final FirebaseCallback firebaseCallback){
        db.collection("Recipes").whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        Map<String, Object> doc = document.getData();
                        Recipe recipe = new Recipe((String)doc.get("Description"), (String)doc.get("Image")); //, (HashMap<String,String[]>)doc.get("Ingredients"), (int) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
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

    private interface FirebaseCallback{
        void onCallback(String recipe);
    }




}
        /*
        db.collection("cities")
        .whereEqualTo("capital", true)
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
DocSnippets.java

         */


        /*
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot){
                if(documentSnapshot.exists()){
                    Recipe recipe = documentSnapshot.toObject(Recipe.class);
                    Log.d(TAG, "recipe added to recipes");
                    recipes.add(recipe);
                }
                else{
                    Log.d(TAG, "Error getting recipes");
                }
            }
        });
    }*/
