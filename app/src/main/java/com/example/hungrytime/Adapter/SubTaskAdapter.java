package com.example.hungrytime.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytime.ModelClass.Task;
import com.example.hungrytime.R;

import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

import static com.example.hungrytime.Utils.UtilFunctions.drawLine;
import static com.example.hungrytime.Utils.UtilFunctions.removeLine;


public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.ViewHolder> {

    ArrayList<Task> arrayList;
    Context context;
    ArrayList<RecyclerView.ViewHolder> myViewHolder = new ArrayList<>();

    public SubTaskAdapter(Context context, ArrayList<Task> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_sub_task_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Task t = arrayList.get(holder.getAdapterPosition());
        holder.title.setText(t.getName());
        if(t.isCompleted()){
            setChecked(holder);
            Log.e("checking _ " , t.getName() + " true : " + t.isCompleted());
        }

        myViewHolder.add(holder);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.checkBoxMain.setOnCheckedChangeListener(new CustomCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CustomCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                    t.setCompleted(true);
                    setChecked(holder);
//                    arrayList.set(holder.getAdapterPosition(),t);
                    Log.e("setting_true_sub_task" , t.getName() + " true : " + t.isCompleted());
                }else{
                    t.setCompleted(false);
                    removeCheck(holder);
//                    arrayList.set(holder.getAdapterPosition(),t);
                    Log.e("setting_false_sub_task" , t.getName() + " true : " + t.isCompleted());

                }
            }
        });
    }

    private void removeCheck(ViewHolder holder) {
        removeLine(holder.title);
        holder.title.setTextColor(context.getResources().getColor(R.color.black));
        holder.lineColor.setBackgroundColor(context.getResources().getColor(R.color.purple));
        if(holder.checkBoxMain.isChecked()){
            holder.checkBoxMain.setChecked(false);
        }
    }

    private void setChecked(ViewHolder holder) {
        drawLine(holder.title);
        holder.title.setTextColor(context.getResources().getColor(R.color.grey));
        holder.lineColor.setBackgroundColor(context.getResources().getColor(R.color.light_purple));
        if(!holder.checkBoxMain.isChecked()){
            holder.checkBoxMain.setChecked(true);
        }
    }

    public void checkAll(){
        for (int i=0;i<myViewHolder.size();i++) {
            setChecked((ViewHolder)myViewHolder.get(i));
        }
    }

    public void removeCheckAll(){
        for (int i=0;i<myViewHolder.size();i++) {
            removeCheck((ViewHolder)myViewHolder.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CustomCheckBox checkBoxMain;
        TextView title;
        View lineColor;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxMain = itemView.findViewById(R.id.checkbox_main);
            title = itemView.findViewById(R.id.txt_main);
            delete=itemView.findViewById(R.id.delete);
            lineColor = itemView.findViewById(R.id.line_color);
        }
    }

}
