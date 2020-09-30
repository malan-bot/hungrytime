package com.example.hungrytime.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungrytime.HomeActivity;
import com.example.hungrytime.ModelClass.Task;
import com.example.hungrytime.ModelClass.TaskHolder;
import com.example.hungrytime.R;

import net.cachapa.expandablelayout.ExpandableLayout;
import net.igenius.customcheckbox.CustomCheckBox;

import java.util.ArrayList;

import static com.example.hungrytime.Utils.UtilFunctions.drawLine;
import static com.example.hungrytime.Utils.UtilFunctions.removeLine;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.mViewHolder> {
    ArrayList<TaskHolder> arrayList;
    Context context;
    MyClickListener myClickListener;
    HomeActivity homeActivity;

    public TaskAdapter(Context context, ArrayList<TaskHolder> taskHolders, MyClickListener myClickListener){
        this.context = context;
        this.arrayList = taskHolders;
        this.myClickListener = myClickListener;
        this.homeActivity = (HomeActivity) context;
    }

    @NonNull
    @Override
    public TaskAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_view_task_adapter,parent,false);
        return new mViewHolder(v, homeActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskAdapter.mViewHolder holder, int position) {


        if(!this.homeActivity.IN_SELECTED_MODE){
            holder.hiddenCheckBox.setVisibility(View.GONE);
            holder.taskLayout.setPadding(0,0,0,0);
            holder.taskLayout.setBackgroundColor(Color.WHITE);
        } else {
            holder.hiddenCheckBox.setVisibility(View.GONE);
            holder.hiddenCheckBox.setChecked(false);
        }


        final TaskHolder obj = arrayList.get(holder.getAdapterPosition());
        holder.title.setText(obj.getTitle().getName());
        if(obj.getTitle().getDesc() != null){
            holder.desc.setText(obj.getTitle().getDesc());
            holder.img.setImageResource(obj.getTitle().getImgRes());
        }
        if(obj.getTitle().isCompleted()){
            setCheck(holder);
        }

        //setting up adapter
        holder.adapter = new SubTaskAdapter(context,arrayList.get(holder.getAdapterPosition()).getSubTasks());
        holder.rvSubTask.setAdapter(holder.adapter);


        holder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.expandableLayout.isExpanded()){
                    holder.expandableLayout.collapse();
                    rotationBack(holder.btnExpand);
                }else{
                    holder.expandableLayout.expand(true);
                    rotation(holder.btnExpand);
                }
            }
        });

        holder.checkBoxMain.setOnCheckedChangeListener(new CustomCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CustomCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                    checkAll(holder,holder.getAdapterPosition());
//                    if(holder.expandableLayout.isExpanded()){
//                        holder.expandableLayout.collapse(true);
//                    }
//                    ArrayList<Task> tempTask = obj.getSubTasks();
//                    for (Task t:tempTask
//                         ) {
//                        t.setCompleted(true);
//                    }
//                    obj.setSubTasks(tempTask);
                    obj.getTitle().setCompleted(true);
                    setCheck(holder);

                }else{
//                    ArrayList<Task> tempTask = obj.getSubTasks();
//                    for (Task t:tempTask
//                    ) {
//                        t.setCompleted(false);
//                    }
//                    obj.setSubTasks(tempTask);

                    removeCheckAll(holder,holder.getAdapterPosition());
                    obj.getTitle().setCompleted(false);
                    removeCheck(holder);
                }
            }
        });
    }

    private void checkAll(mViewHolder holder,int pos) {
        for (Task t:arrayList.get(pos).getSubTasks()){
            t.setCompleted(true);
        }
        holder.adapter.checkAll();
    }
    private void removeCheckAll(mViewHolder holder,int pos) {
        for (Task t:arrayList.get(pos).getSubTasks()){
            t.setCompleted(false);
        }
        holder.adapter.removeCheckAll(); //to remove check all
    }

    void setCheck(mViewHolder holder){
        drawLine(holder.title);
        holder.title.setTextColor(context.getResources().getColor(R.color.grey));
        holder.txtCompleted.setVisibility(View.VISIBLE);
        if(!holder.checkBoxMain.isChecked()){
            holder.checkBoxMain.setChecked(true);
        }
    }
    void removeCheck(mViewHolder holder){
        removeLine(holder.title);
        holder.title.setTextColor(context.getResources().getColor(R.color.black));
        holder.txtCompleted.setVisibility(View.GONE);
        if(holder.checkBoxMain.isChecked()){
            holder.checkBoxMain.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface MyClickListener{
        void myListener(View view, int adapterPosition);
    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CustomCheckBox checkBoxMain;
        TextView title;
        TextView desc;
        ImageView img;
        ImageButton btnExpand,btnShare,btnEdit;
        ExpandableLayout expandableLayout;
        TextView txtCompleted;

        RecyclerView rvSubTask;
        SubTaskAdapter adapter;

        public LinearLayout foregroundView;
        public RelativeLayout backgroundView;

        // This is for handling the click events of check boxes
        public HomeActivity homeActivity;
        // Hidden checkboxes
        public CheckBox hiddenCheckBox;
        // For Selecting task mode I have to access the layout of each task
        // which is a frame layout and then implement a onLongClickListener
        public FrameLayout taskLayout;


        public mViewHolder(@NonNull View itemView, final HomeActivity homeActivity) {
            super(itemView);
            checkBoxMain = itemView.findViewById(R.id.checkbox_main);

            title = itemView.findViewById(R.id.txt_main);
            desc = itemView.findViewById(R.id.txt_desc);
            img = itemView.findViewById(R.id.img_view_task_adapter);

            btnExpand = itemView.findViewById(R.id.btn_expand);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            txtCompleted = itemView.findViewById(R.id.txt_compete);
            btnShare = itemView.findViewById(R.id.btn_share);
            btnEdit = itemView.findViewById(R.id.btn_edit);

            hiddenCheckBox = itemView.findViewById(R.id.task_hidden_checkbox);
            taskLayout = itemView.findViewById(R.id.task_layout);

            // Giving a longClickListener to the taskLayout from homeActivity as I implemented the
            // View.onLongClickListener on the HomeActivity

            taskLayout.setOnLongClickListener(homeActivity);

            rvSubTask = itemView.findViewById(R.id.rv_subTask);
            RecyclerView.LayoutManager layoutParams = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
            rvSubTask.setLayoutManager(layoutParams);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvSubTask.getContext(),
                    DividerItemDecoration.VERTICAL);
            rvSubTask.addItemDecoration(dividerItemDecoration);

            foregroundView = itemView.findViewById(R.id.view_foreground);
            backgroundView = itemView.findViewById(R.id.view_background);

            btnShare.setOnClickListener(this);
            btnEdit.setOnClickListener(this);


            taskLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(homeActivity.IN_SELECTED_MODE == true){
                        homeActivity.preSelectionForTask(v, getAdapterPosition());
                    }
                }
            });
            hiddenCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeActivity.prepareSelection(v, getAdapterPosition());
                }
            });

            this.homeActivity = homeActivity;

        }

        @Override
        public void onClick(View view) {
            myClickListener.myListener(view,getAdapterPosition());
        }
    }

    void rotation(View view){
        view.animate().rotation(0).setDuration(200).start();
    }
    void rotationBack(View view){
        view.animate().rotation(180).setDuration(200).start();
    }

    // Delete a Task method
    // This method removes the obj from your arraylist and then notifies the adapter

    public void removeItem(int position){
        arrayList.remove(position);
        notifyItemRemoved(position);
    }

    // Undo deleted method function
    // This method adds the removed obj from arraylist into the arraylist and then notifies the adapter

    public void restoreItem(TaskHolder taskHolder, int position){
        arrayList.add(position, taskHolder);
        notifyItemInserted(position);
    }

    // Deletes the selected multiple items
    public void updateAdapter(ArrayList<TaskHolder> list){
        for(TaskHolder taskHolder : list){
            arrayList.remove(taskHolder);
        }
        notifyDataSetChanged();
    }

}
