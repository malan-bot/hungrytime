/***

package com.example.hungrytime;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytime.CategoriesAdapter.TaskAdapter;
import com.example.hungrytime.Helper.TaskRclyViewTouchHelperListener;
import com.example.hungrytime.ModelClass.Task;
import com.example.hungrytime.ModelClass.TaskHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pixplicity.easyprefs.library.Prefs;


import java.util.ArrayList;

import io.paperdb.Paper;

public abstract class HomeActivity extends AppCompatActivity implements TaskAdapter.MyClickListener, TaskRclyViewTouchHelperListener, View.OnLongClickListener{

    RecyclerView rvTask;
    FloatingActionButton fab;    //floating action button to create new task
    ImageButton btnLogout;

    TaskAdapter taskAdapter;
    public static ArrayList<TaskHolder> arrayList;
    CoordinatorLayout coordinatorLayout;


    public boolean IN_SELECTED_MODE = false;
    TextView tasksCounter;
    TextView appTitle;
    Toolbar toolbar;
    ArrayList<TaskHolder> selection_arrayList;
    int selection_counter = 0;
    int bgColor = Color.TRANSPARENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        listeners();
        getSaveBooks();
    }

    private void getSaveBooks() {
        //if there exists book in the local storage load them into the app
        if(Paper.book("task").contains("key")){
            arrayList = Paper.book("task").read("key");
            setUpAdapter();
        }
    }

    private void listeners() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //start new activity
//                Log.e("Before Adavancing"," NWO");
//                for (TaskHolder t:
//                     arrayList) {
//                    Log.e("Task_name : ", t.getTitle().getName() + " condition " + t.getTitle().isCompleted());
//                    for (Task tt: t.getSubTasks()){
//                        Log.e("SUB_task_name : ", tt.getName() + " condition " + tt.isCompleted());
//                    }
//                }
                startActivity(new Intent(HomeActivity.this,AddTask.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs.putBoolean("is_login", false);
//                startActivity(new Intent(HomeActivity.this, SignIn.class));
                finish();
            }
        });
    }

    private void init() {
        rvTask = findViewById(R.id.rv_task);
        RecyclerView.LayoutManager layoutParams = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvTask.setLayoutManager(layoutParams);

        //  process to delete
        rvTask.setItemAnimator(new DefaultItemAnimator());
        rvTask.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        fab = findViewById(R.id.fab);
        btnLogout = findViewById(R.id.btn_logout);
        coordinatorLayout = findViewById(R.id.coordinator);
        arrayList = new ArrayList<>();

        tasksCounter = findViewById(R.id.tasks_counter);
        appTitle = findViewById(R.id.app_title);
        tasksCounter.setVisibility(View.GONE);
        appTitle.setVisibility(View.VISIBLE);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        selection_arrayList = new ArrayList<>();


        /*
        * Preference manager is used to help with the working of
        * shared preferences It is handy to use
        * it is only used to know that user is still login or not
        * */

/**
        new Prefs.Builder() //  this is used to initialize the preference helper
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(false)
                .build();


        Paper.init(this);
        setUpAdapter();

    }

    private void setUpAdapter() {   //attach the adapter to the recycler view

//        <item>Browse</item>
//        <item>View Schedule</item>
//        <item>Shopping List</item>
//        <item>Edit Account</item>
//
//        <item>Browse dishes</item>
//        <item>View and Plan Weekly Schedule</item>
//        <item>Update Shopping List</item>
//        <item>Update Account</item>

        if(Paper.book().read("firstTime") == null){
            Paper.book().write("firstTime", true);
        }
        if(Paper.book().read("firstTime").equals(true)){
            arrayList = new ArrayList<>();
            arrayList.add(new TaskHolder(new Task("Browser", false, R.drawable.search, "Browse Dishes"), new ArrayList<Task>()));
            arrayList.add(new TaskHolder(new Task("View Schedule", false, R.drawable.calendar, "View and Plan Weekly Schedule"), new ArrayList<Task>()));
            arrayList.add(new TaskHolder(new Task("Shopping List", false, R.drawable.shopping, "Update Shopping List"), new ArrayList<Task>()));
            arrayList.add(new TaskHolder(new Task("Edit Account", false, R.drawable.edit, "Update Account"), new ArrayList<Task>()));
            Paper.book("task").write("key", arrayList);
            Paper.book().write("firstTime", false);
        }
        taskAdapter = new TaskAdapter(this,arrayList,this);
        rvTask.setAdapter(taskAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        /*
        * Update the recycler view so that the newly updated task must be added to the list*/

/**
        setUpAdapter();
    }

    // Implementing the function of my interface located in Helper package used to swipe left to delete
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if(viewHolder instanceof  TaskAdapter.mViewHolder){

            String title = arrayList.get(viewHolder.getAdapterPosition()).getTitle().getName();

            final TaskHolder deletedTask = arrayList.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();
            taskAdapter.removeItem(deleteIndex);

            /*
            * Snackbar is better way to notify user
            * */

/**
            Snackbar snackbar = Snackbar.make(coordinatorLayout, title + " removed from to do list.", Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    taskAdapter.restoreItem(deletedTask, deleteIndex);
                }
            });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    @Override
    protected void onDestroy() {
        /*
        * Save all the task in the local storage
        * when the app is closing stage
        * */

/**
        Paper.book("task").destroy();
        Paper.book("task").write("key",arrayList);
        Log.e("saving_task","asdasd");
        super.onDestroy();
    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_selected_mode);
        tasksCounter.setVisibility(View.VISIBLE);
        appTitle.setVisibility(View.GONE);
        IN_SELECTED_MODE = true;
        // Due to this function checkboxes will be shown to us but we don't want that
        // This showing of checkboxes is only for checking purposes
        taskAdapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view, int position){
        if(((CheckBox)view).isChecked()){
            selection_arrayList.add(arrayList.get(position));
            selection_counter += 1;
            updateSelectionCounter(selection_counter);
        } else {
            selection_arrayList.remove(arrayList.get(position));
            selection_counter -= 1;
            updateSelectionCounter(selection_counter);
        }
    }

    public void preSelectionForTask(View view, int position){
        FrameLayout frameLayout = (FrameLayout)view;
        Drawable bg = frameLayout.getBackground();
        if(bg instanceof ColorDrawable){
            bgColor = ((ColorDrawable) bg).getColor();
        }

        // It item is already selected then un-select it
        if(bgColor == Color.DKGRAY){
            Log.d("ColorInfo", "The color is : Light Gray" );
            selection_arrayList.remove(arrayList.get(position));
            selection_counter -= 1;
            updateSelectionCounter(selection_counter);
            //frameLayout.setBackgroundColor(Color.WHITE);
            view.setBackgroundColor(Color.WHITE);
            view.setPadding(0,0,0,0);
        } else {
            Log.d("ColorInfo", "The color is : White");
            // If item is not already selected then do this
            selection_arrayList.add(arrayList.get(position));
            selection_counter += 1;
            updateSelectionCounter(selection_counter);
            //frameLayout.setBackgroundColor(Color.DKGRAY);
            view.setBackgroundColor(Color.DKGRAY);
            view.setPadding(10,10,10,10);
        }
    }

    public void updateSelectionCounter(int counter){
        if(counter == 0){
            tasksCounter.setText("0 Items Selected");
        } else {
            tasksCounter.setText( counter + " Items Selected");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_selected_item_btn){
            TaskAdapter newAdapter = (TaskAdapter) taskAdapter;
            newAdapter.updateAdapter(selection_arrayList);
            clearSelectedMode();
        }
        else if (item.getItemId() == android.R.id.home){
            clearSelectedMode();
            taskAdapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearSelectedMode(){
        IN_SELECTED_MODE = false;
        toolbar.getMenu().clear();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        tasksCounter.setVisibility(View.GONE);
        appTitle.setVisibility(View.VISIBLE);
        tasksCounter.setText("0 Items Selected");
        selection_counter = 0;
        selection_arrayList.clear();

    }

    @Override
    public void onBackPressed() {
        if(IN_SELECTED_MODE){
            clearSelectedMode();
            taskAdapter.notifyDataSetChanged();
        } else {
            super.onBackPressed();
        }
    }



}
**/