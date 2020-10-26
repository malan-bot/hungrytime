package com.example.hungrytime.Helper;

import androidx.recyclerview.widget.RecyclerView;

public interface TaskRclyViewTouchHelperListener {

    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
