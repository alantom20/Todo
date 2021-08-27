package com.chenhao.todo.views;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenhao.todo.R;






public class TaskGroupIconAdapter extends RecyclerView.Adapter<TaskGroupIconAdapter.TaskGroupIconHolder>{
    FragmentManager fragmentManager;



    public TaskGroupIconAdapter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

    }

    @NonNull
    @Override
    public TaskGroupIconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_group_icon,parent,false);
        return new TaskGroupIconHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskGroupIconHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                for (Fragment fragment : fragmentManager.getFragments()) {
                    if(fragment != null)
                        fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TaskGroupIconHolder extends RecyclerView.ViewHolder{
       // FloatingActionButton imageFab;
        TextView countText;
        TextView titleText;
        ProgressBar taskPr;

        public TaskGroupIconHolder(@NonNull View itemView) {
            super(itemView);

            countText = itemView.findViewById(R.id.text_tasks_total_square);
            titleText = itemView.findViewById(R.id.text_tasks_title_square);
            taskPr = itemView.findViewById(R.id.progressBar_task_square);
           // imageFab = itemView.findViewById(R.id.fab_image);
        }
    }
}
