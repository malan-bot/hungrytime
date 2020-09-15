package com.example.hungrytime;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeRecView;
    public static ArrayList<Recipe> recipes;
    public final String TAG = "Recipes";

    //private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Recipes/");
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRecView = findViewById(R.id.recipeRecView);
        recipes = new ArrayList<>();
        fetchRecipe("Mimosa");


        RecipeViewAdapter adapter = new RecipeViewAdapter(this);
        adapter.setRecipes(recipes);
        recipeRecView.setAdapter(adapter);
        recipeRecView.setLayoutManager(new GridLayoutManager(this,2));

    }
    public void fetchRecipe(String item) {
        db.collection("Recipes").whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        String description = document.getString("Description");
                        String imageUrl = document.getString("Image");
                        recipes.add(new Recipe(description, imageUrl));
                        Log.d(TAG, Integer.toString(recipes.size()));
                        return;
                    }
                }
                else{
                    Log.d(TAG, "Error getting documents: ", task.getException());
                    return;
            }
        }
    });


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
    }

}