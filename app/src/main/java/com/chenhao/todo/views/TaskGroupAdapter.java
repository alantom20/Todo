package com.chenhao.todo.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenhao.todo.R;
import com.chenhao.todo.models.TaskGroup;

import java.util.List;


public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.TaskGroupHolder> {


    List<TaskGroup> taskGroups;

    public TaskGroupAdapter(List<TaskGroup> taskGroups) {
        this.taskGroups = taskGroups;
    }

    @NonNull
    @Override
    public TaskGroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_group,parent,false);
        return new TaskGroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskGroupHolder holder, int position) {
        TaskGroup taskGroup = taskGroups.get(position);
        holder.countText.setText(String.valueOf(taskGroup.getTotal()));
        holder.titleText.setText(taskGroup.getTaskGroup());
        holder.taskPr.setMax(taskGroup.getTotal());
        holder.taskPr.setProgress(taskGroup.getIsDone());


    }

    @Override
    public int getItemCount() {
        return taskGroups.size();
    }

    public class TaskGroupHolder extends RecyclerView.ViewHolder{
        TextView countText;
        TextView titleText;
        ProgressBar taskPr;
        public TaskGroupHolder(@NonNull View itemView) {
            super(itemView);
            countText = itemView.findViewById(R.id.text_tasks_total);
            titleText = itemView.findViewById(R.id.text_tasks_title);
            taskPr = itemView.findViewById(R.id.progressBar_task);

        }
    }
}
