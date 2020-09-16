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
import java.util.Iterator;
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

        readData("Alcohol", new FirebaseCallback() {
            @Override
            public void onCallback(String list) {
                Log.d(TAG, list.toString());
            }
        });
    }

    //TODO revise readData to create a complete Recipe object -- partially complete: likeCount & tags remain

    //TODO function to load recipes into our Firestore db --

    //TODO function to read Ingredient info --

    //TODO function to load ingredients into our Firestore db --

    private void readData(String item, final FirebaseCallback firebaseCallback){
        db.collection("Recipes").whereArrayContains("tags", item).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) { if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        Log.d(TAG, document.getId() + " => " + document.getData().get("Ingredients").getClass());

                        Map<String, Object> doc = document.getData();
                        ArrayList<HashMap<String, ArrayList>> fetchedIngredients = (ArrayList<HashMap<String, ArrayList>>)doc.get("Ingredients");
                        HashMap<String, ArrayList> ingredients = new HashMap<String, ArrayList>();

                        //Parses through the fetched ingredients arraylist
                        for(HashMap<String, ArrayList> set : fetchedIngredients){
                            Iterator entries = set.entrySet().iterator();
                            while(entries.hasNext()) {
                                Map.Entry entry = (Map.Entry) entries.next();
                                String item = (String)entry.getValue();
                                entry = (Map.Entry)entries.next();
                                ArrayList qty = (ArrayList)entry.getValue();
                                ingredients.put(item, qty);
                                Log.d(TAG, "key = " + item + ", value = " + qty.get(1) + " " + qty.get(0));
                            }
                        }
                        Log.d(TAG, "ingredients hashmap: " + ingredients.toString());



                        Recipe recipe = new Recipe((String)doc.get("Description"), (String)doc.get("Image"), ingredients, (long)doc.get("likeCount"),(ArrayList<String>)doc.get("tags"));//,(HashMap<String,String[]>)doc.get("Ingredients"), (int) doc.get("likeCount"), (ArrayList<String>) doc.get("tags"));
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
