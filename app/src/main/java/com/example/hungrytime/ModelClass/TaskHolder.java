package com.example.hungrytime.ModelClass;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskHolder implements Serializable {

    public TaskHolder() { }

    public TaskHolder(Task task , ArrayList<Task> subTasks ) { this.title  = task;  this.subTasks = subTasks; }

    public Task getTitle() {
        return title;
    }

    public void setTitle(Task title) {
        this.title = title;
    }

    public ArrayList<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<Task> subTasks) {
        this.subTasks = subTasks;
    }

    Task title;
    ArrayList<Task> subTasks;
}
