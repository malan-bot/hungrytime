/***package com.example.Browse_Mehul_Garg;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private ArrayList<Categories> categories = new ArrayList<>();
    private Context context;

    public CategoriesAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.txtname.setText(categories.get(position).getName());
        holder.image.setImageResource(categories.get(position).getImageUrl());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubBrowse.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtname;
        private ImageView image;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.txtName);
            parent = itemView.findViewById(R.id.parent);
            image= itemView.findViewById(R.id.image);


        }


    }
}
***/