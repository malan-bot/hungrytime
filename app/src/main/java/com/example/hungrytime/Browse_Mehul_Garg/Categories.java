package com.example.Browse_Mehul_Garg;

public class Categories {
    private String name;
    private int  imageUrl;

    public Categories(String name, int imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                '}';
    }
}
