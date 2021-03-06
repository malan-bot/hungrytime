/***
package com.example.hungrytime.CategoriesAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytime.HomeActivity;
import com.example.hungrytime.R;


public class MyAdapter extends RecyclerView.CategoriesAdapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
    Context context;

    public MyAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText1.setText(data1[position]);
        holder.myImage.setImageResource(images[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("Title", holder.myText1.getText());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 =itemView.findViewById(R.id.myText1);
            myText2 =itemView.findViewById(R.id.myText2);
            myImage =itemView.findViewById(R.id.myImageView);
            layout = itemView.findViewById(R.id.constraintLayout_myRow);
        }

    }

}
 **/

