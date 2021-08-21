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

public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.TaskButtonHolder> {
    List<TaskGroup> taskButtons;

    public TaskGroupAdapter(List<TaskGroup> taskButtons) {
        this.taskButtons = taskButtons;
    }

    @NonNull
    @Override
    public TaskButtonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_group,parent,false);
        return new TaskButtonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskButtonHolder holder, int position) {
        TaskGroup taskButton = taskButtons.get(position);
        holder.titleText.setText(taskButton.getTitle());
        holder.countText.setText(String.valueOf(taskButton.getTaskCount()));
        holder.taskPr.setMax(taskButton.getTaskCount());
        holder.taskPr.setProgress(taskButton.getTaskProgress());



    }

    @Override
    public int getItemCount() {
        return taskButtons.size();
    }

    public class  TaskButtonHolder extends RecyclerView.ViewHolder{
        TextView countText;
        TextView titleText;
        ProgressBar taskPr;
        public TaskButtonHolder(@NonNull View itemView) {
            super(itemView);
            countText = itemView.findViewById(R.id.text_tasks_total);
            titleText = itemView.findViewById(R.id.text_tasks_title);
            taskPr = itemView.findViewById(R.id.progressBar_task);

        }
    }
}
