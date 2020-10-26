package com.example.hungrytime;

import java.util.Comparator;

public class CategoriesModel {

    private String title;
    private int img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public static final Comparator<CategoriesModel> By_TITLE_ASCENDING= new Comparator<CategoriesModel>() {
        @Override
        public int compare(CategoriesModel o1, CategoriesModel o2) {

            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static final Comparator<CategoriesModel> By_TITLE_DESCENDING = new Comparator<CategoriesModel>() {
        @Override
        public int compare(CategoriesModel o1, CategoriesModel o2) {

            return o2.getTitle().compareTo(o1.getTitle());
        }
    };
}
