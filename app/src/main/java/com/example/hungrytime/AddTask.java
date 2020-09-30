package com.example.hungrytime;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hungrytime.ModelClass.Task;
import com.example.hungrytime.ModelClass.TaskHolder;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;



import java.util.ArrayList;

public class AddTask extends AppCompatActivity {

    private static final String TAG = "AddTask";
    ImageButton btnBack,btnCreate;
    TextView txtTitle;
    ImageButton btnAddSubTask;
    TextInputEditText edTaskName;
    public Task task;

    /*
    * New task is added using a dialog box
    * */
    TextInputEditText edSubTaskDialog;
    TextInputEditText edSubTaskDialogQty;

    MaterialButton btnCancelDialog, btnCreateSubTaskDialog;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    LinearLayout linearLayoutSubTask;   // the root view to which child views are added, the child view representing sub-tasks
    ArrayList<EditText> edList = new ArrayList<>(); // to save reference of all the sub-layout added in the root linera-layout

    int POSITION_EDIT = -1; //it is used to see if the activity is on edit mode, -1 = non_edit mode so new task process follows
    // if it contains other than -1 it represents position of the element of the arraylist task in the Homeactivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        task = new Task();
        init();
        listeners();

        POSITION_EDIT = getIntent().getIntExtra("position",-1); //check if the position is passed to the intent representing the edit mode
        if(POSITION_EDIT != -1){
            Log.e("position",POSITION_EDIT+"");
            populateViews();    //if there is a position var has passed then populate the views in the view
        }
    }

    private void populateViews() {
        TaskHolder th = HomeActivity.arrayList.get(POSITION_EDIT);
        edTaskName.setText(th.getTitle().getName());
        for (Task t:th.getSubTasks()
             ) {
            addLayout(t.getName());
        }
    }

    private void listeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discardDialog();    // to notify user that you are swiping the screen without saving the changes
            }
        });

        btnAddSubTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validations()){
                    addToList();
                    Log.e("now_finalizing", "asd");
                }
            }
        });

        dialogListener();
    }

    private void addToList() {
        TaskHolder taskHolder = new TaskHolder();
        taskHolder.setTitle(usmFunc(edTaskName.getText().toString()));
        Log.e(TAG, "addToList: " + taskHolder.getTitle().getName() + "  -  " + taskHolder.getTitle().getDesc() );
        ArrayList<Task> arrayList = new ArrayList<>();
        for (EditText ed:edList) {
            Log.e("adding_",ed.getText().toString());
            arrayList.add(new Task(ed.getText().toString()));
        }
        taskHolder.setSubTasks(arrayList);
        if(POSITION_EDIT == -1) {   //if the task is in edit mode or not
            HomeActivity.arrayList.add(taskHolder);
        }else{
            HomeActivity.arrayList.set(POSITION_EDIT,taskHolder);
        }
        finish();
    }

    /*
    * Text Exceptions*/
    private boolean validations() {
        if(edTaskName.getText().toString().equals("")){
            edTaskName.setError("Can't be empty");
            return false;
        }

        for (EditText ed:edList) {
            if(ed.getText().toString().equals("")){
                ed.setError("Can't be empty");
                return false;
            }
        }
        return true;
    }

    private void dialogListener() {
        btnCancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnCreateSubTaskDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edSubTaskDialog.getText().toString().equals("")){
                    edSubTaskDialog.setError("Can't be empty");
                }else{
                    if(edSubTaskDialogQty.getText().toString().equals("")){
                        edSubTaskDialogQty.setError("Can't be empty");
                    } else {
                        addLayout(edSubTaskDialog.getText().toString() + "  -  " + edSubTaskDialogQty.getText().toString());
                        dialog.dismiss();
                        edSubTaskDialog.setText("");
                        edSubTaskDialogQty.setText("");
                        //add task
                    }
                }
            }
        });

    }

    private void addLayout(String str) {

        /*
        * adding the layout representing the new sub-task
        * added the reference in the array list edit text
        * then set the text put in the dialog box to the
        * edit text in the view */

        View child = getLayoutInflater().inflate(R.layout.snippet_add_sub_task, null);
        LinearLayout linTemp = (LinearLayout) child;
        EditText edTemp = (EditText) linTemp.getChildAt(1);
        if(POSITION_EDIT == -1) {   //if the task is in edit mode
            edTemp.setText(edSubTaskDialog.getText() + "    -    " + edSubTaskDialogQty.getText());
        }else{
            edTemp.setText(str);
        }
        edList.add(edTemp);
        linearLayoutSubTask.addView(child);
    }

    private void init() {
        btnBack = findViewById(R.id.btn_back);
        btnCreate = findViewById(R.id.btn_create);
        txtTitle = findViewById(R.id.txt_title);
        btnAddSubTask = findViewById(R.id.btn_add_sub);
        linearLayoutSubTask = findViewById(R.id.lin_lay_sub_task);
        edTaskName = findViewById(R.id.ed_tast_name);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_sub_task, null);

        edSubTaskDialog = dialogView.findViewById(R.id.ed_dialog_sub_task);
        edSubTaskDialogQty = dialogView.findViewById(R.id.ed_dialog_sub_task_qty);

        btnCancelDialog = dialogView.findViewById(R.id.btn_cancel);
        btnCreateSubTaskDialog = dialogView.findViewById(R.id.btn_ok);

        builder = new AlertDialog.Builder(AddTask.this);
        builder.setView(dialogView);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    void discardDialog(){
        new MaterialAlertDialogBuilder(AddTask.this)
                .setTitle("Are you Sure")
                .setMessage("The changes made to this task will be saved")
                .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .create().
                show();
    }
    @Override
    public void onBackPressed() {
        discardDialog();
    }

    private Task usmFunc(String taskName){
        Task task = new Task();
        if(taskName.equals("Browser")){
//            task = new Task("Browser", false, R.drawable.search, "Browse Dishes");
            task.setName("Browser");
            task.setDesc("Browse Dishes");
            task.setImgRes(R.drawable.search);
            task.setCompleted(false);
            return task;
        } else if ( taskName.equals("View Schedule") ){
//            task = new Task("View Schedule", false, R.drawable.calendar, "View and Plan Weekly Schedule");
            task.setName("View Schedule");
            task.setDesc("View and Plan Weekly Schedule");
            task.setImgRes(R.drawable.calendar);
            task.setCompleted(false);
            return task;
        } else if ( taskName.equals("Shopping List") ){
//            task = new Task("Shopping List", false, R.drawable.shopping, "Update Shopping List");
            task.setName("Shopping List");
            task.setDesc("Update Shopping List");
            task.setImgRes(R.drawable.shopping);
            task.setCompleted(false);
            return task;
        } else if ( taskName.equals("Edit Account") ){
//            task = new Task("Edit Account", false, R.drawable.edit, "Update Account");
            task.setName("Edit Account");
            task.setDesc("Update Account");
            task.setImgRes(R.drawable.edit);
            task.setCompleted(false);
            return task;
        }
        return task;
    }

}
