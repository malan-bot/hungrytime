package com.example.hungrytime.ModelClass;

public class Task {

    public Task() {
    }

    public Task(String name, boolean isCompleted, int imgRes, String desc) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.imgRes = imgRes;
        this.desc = desc;
    }

    public Task(String name){
        this.name = name;
        isCompleted = false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    String name;
    boolean isCompleted;
    int imgRes;
    String desc;
}
