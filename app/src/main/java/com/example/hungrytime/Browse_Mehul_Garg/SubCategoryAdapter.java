/**package com.example.Browse_Mehul_Garg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private ArrayList<Categories> subCat = new ArrayList<>();
    private Context context;

    public SubCategoryAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sub_browse, parent, false);
        SubCategoryAdapter.ViewHolder holder2 = new SubCategoryAdapter.ViewHolder(view);
        return holder2;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder2, final int position) {
        holder2.txtSubName.setText(subCat.get(position).getName());
        holder2.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, subCat.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return subCat.size();
    }

    public void setSubCat(ArrayList<Categories> subCat) {
        this.subCat = subCat;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtSubName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubName = itemView.findViewById(R.id.txtSubName);
            parent = itemView.findViewById(R.id.parent);
        }
    }

}
**/