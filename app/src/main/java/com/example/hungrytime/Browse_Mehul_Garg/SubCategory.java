package com.example.Browse_Mehul_Garg;

public class SubCategory {

    private String subName;

    public SubCategory(String subName, int imageSub) {
        this.subName = subName;
    }

    public String getSubName() {

        return subName;
    }

    public void setSubName(String subName) {

        this.subName = subName;
    }



    @Override
    public String toString() {
        return "SubCategory{" +
                "subName='" + subName + '\'' +
                '}';
    }
}
