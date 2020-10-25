package com.example.hungrytime;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Categories extends AppCompatActivity {

    RecyclerView mRecyclerView;
    CategoriesAdapter myCategoriesAdapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        mRecyclerView = findViewById(R.id.recyclerView2);
        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);

        getMyList();
    }

    private void getMyList() {

        ArrayList<CategoriesModel> categoriesModels = new ArrayList<>();

        CategoriesModel m = new CategoriesModel();
        m.setTitle("Alcoholic Drinks");
        m.setImg(R.drawable.alcohol);
        categoriesModels.add(m);

        m = new CategoriesModel();
        m.setTitle("Smoothie");
        m.setImg(R.drawable.smoothie);
        categoriesModels.add(m);

        m = new CategoriesModel();
        m.setTitle("Juice");
        m.setImg(R.drawable.juice);
        categoriesModels.add(m);

        String mSortSetting = preferences.getString("Sort", "ascending");

        if(mSortSetting.equals("ascending"))
        {
            Collections.sort(categoriesModels, CategoriesModel.By_TITLE_ASCENDING);
        }
        else if(mSortSetting.equals("descending"))
        {
            Collections.sort(categoriesModels, CategoriesModel.By_TITLE_DESCENDING);

        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myCategoriesAdapter = new CategoriesAdapter(this, categoriesModels);
        mRecyclerView.setAdapter(myCategoriesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sorting)
        {
            sortDailog();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDailog() {
        String[] options = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0)
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "ascending");
                    editor.apply();
                    getMyList();

                }
                else if(which ==1)
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "descending");
                    editor.apply();
                    getMyList();

                }

            }
        });

        builder.create().show();
    }
}