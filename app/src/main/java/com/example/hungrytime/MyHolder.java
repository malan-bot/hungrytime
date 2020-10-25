package com.example.hungrytime;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImageView;
    TextView mTitle;
    CategoriesItemClickListener categoriesItemClickListener;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView= itemView.findViewById(R.id.imageIv);
        this.mTitle= itemView.findViewById(R.id.titleTv);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        this.categoriesItemClickListener.onItemClickListener(v,getLayoutPosition());
    }

    public void setCategoriesItemClickListener(CategoriesItemClickListener ic){

        this.categoriesItemClickListener = ic;
    }

}
