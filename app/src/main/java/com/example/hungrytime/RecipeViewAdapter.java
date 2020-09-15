package com.example.hungrytime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.ViewHolder> {

    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Context context;

    public RecipeViewAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewAdapter.ViewHolder holder, final int position) {
        holder.recipeName.setText(recipes.get(position).getDescription());
        holder.parent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context, recipes.get(position).getDescription() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context)
                .asBitmap()
                .load(recipes.get(position).getImageUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }


    public void setRecipes(ArrayList<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView image;
        private TextView recipeName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeName);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
