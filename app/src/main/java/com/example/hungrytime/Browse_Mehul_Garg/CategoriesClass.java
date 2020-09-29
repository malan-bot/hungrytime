package com.example.Browse_Mehul_Garg;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoriesClass extends AppCompatActivity {

    private RecyclerView contactsRecView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecView = findViewById(R.id.contactsRecView);

        ArrayList<Categories> categories = new ArrayList<>();
        categories.add(new Categories("ALCOHOL", R.drawable.alcohol));
        categories.add(new Categories("SMOOTHIE", R.drawable.smoothie));
        categories.add(new Categories("JUICE", R.drawable.juice));


        CategoriesAdapter adapter = new CategoriesAdapter(this);
        adapter.setCategories(categories);

        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new LinearLayoutManager(this));
//        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));
//        contactsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        Intent view = getIntent();
    }
}