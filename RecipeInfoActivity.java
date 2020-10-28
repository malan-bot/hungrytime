package com.example.hungrytime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RecipeInfoActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference recipeRef;

    String Description;

    TextView recipeName;
    ImageView recipeImage;

    LinearLayout ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        recipeName = findViewById(R.id.recipeName);
        recipeImage = findViewById(R.id.recipeImage);
        ingredientList = findViewById(R.id.ingredientList);

        Intent intent = getIntent();
        Description = intent.getStringExtra("description");

        recipeRef = db.collection("Recipes").document(Description);

        setInfo();
    }

    void setInfo(){
        db.collection("Recipes").document(Description).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.exists()){
                    Map<String, Object> doc = value.getData();
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

                    Glide.with(RecipeInfoActivity.this)
                            .asBitmap()
                            .load(doc.get("Image"))
                            .into(recipeImage);

                    recipeName.setText(doc.get("Description").toString());

                    for (Map.Entry<String, ArrayList> entry : ingredients.entrySet()) {
                        String key = entry.getKey();
                        ArrayList values = entry.getValue();

                        LinearLayout parent = new LinearLayout(RecipeInfoActivity.this);
                        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        parent.setOrientation(LinearLayout.HORIZONTAL);

                        TextView valueText = new TextView(RecipeInfoActivity.this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 15, 0, 0);
                        valueText.setTextSize(16);
                        valueText.setLayoutParams(params);

                        valueText.setText(values.get(1).toString() + " " + values.get(0).toString() + " " + key.toString());

                        parent.addView(valueText);

                        ingredientList.addView(parent);
                    }
                }
            }
        });

    }
}