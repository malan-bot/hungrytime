package com.example.hungrytime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.RecipeVH> {

    ArrayList<Day> scheduledRecipes;

    public ScheduleAdapter(ArrayList<Day> schedule) {
        this.scheduledRecipes = schedule;
    }

    @NonNull
    @Override
    public ScheduleAdapter.RecipeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_view, parent, false);
        return new RecipeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeVH holder, int position) {
        Day day = scheduledRecipes.get(position);
        String recipesString = "";
        for(Recipe recipe : day.getRecipes()) {
            recipesString += "▶ " +recipe.getDescription() + "\n";
        }
        holder.titleTextView.setText(day.getDay());
        holder.recipe.setText(recipesString);

        boolean isExpanded = scheduledRecipes.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


    }

    @Override
    public int getItemCount() {
        return scheduledRecipes.size();
    }

    class RecipeVH extends RecyclerView.ViewHolder {

        ConstraintLayout expandableLayout;
        TextView titleTextView, recipe;

        public RecipeVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            recipe = itemView.findViewById(R.id.recipe);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Day day  = scheduledRecipes.get(getAdapterPosition());
                    day.setExpanded(!day.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}

