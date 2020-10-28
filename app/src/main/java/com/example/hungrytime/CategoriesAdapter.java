package com.example.hungrytime;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<CategoriesModel> categoriesModels;

    public CategoriesAdapter(Context c, ArrayList<CategoriesModel> categoriesModels) {
        this.c = c;
        this.categoriesModels = categoriesModels;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {

        myHolder.mTitle.setText(categoriesModels.get(i).getTitle());
        myHolder.mImageView.setImageResource(categoriesModels.get(i).getImg());

        myHolder.setCategoriesItemClickListener(new CategoriesItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gTitle = categoriesModels.get(position).getTitle();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)myHolder.mImageView.getDrawable();

                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();

                Intent intent = new Intent(c, LoadingRecipes.class);
                intent.putExtra("iTitle", gTitle);
                c.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoriesModels.size();
    }

}
