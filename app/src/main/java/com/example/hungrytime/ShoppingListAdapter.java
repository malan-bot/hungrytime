package com.example.hungrytime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ItemVH> {

    ArrayList<Item> items;
    private Context context;

    public ShoppingListAdapter(Context context) {
        this.context = context;
    }

    public ShoppingListAdapter.ItemVH onCreateViewHolder(ViewGroup parent, int viewTtype) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_view, parent, false);
        return new ItemVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        Item item = items.get(position);
        String itemString = "";
        for(String string : item.getRecipes()) {
            itemString += "▶ " + string + "\n";
        }

        holder.titleTextView.setText(item.getItem());
        holder.item.setText(itemString);

        boolean isExpanded = items.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    public void setShoppingList(ShoppingList shopList){
        this.items = shopList.getShoppingItems();
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemVH extends RecyclerView.ViewHolder{

        ConstraintLayout expandableLayout;
        TextView titleTextView, item;

        public ItemVH(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            item = itemView.findViewById(R.id.item);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Item item  = items.get(getAdapterPosition());
                    item.setExpanded(!item.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
