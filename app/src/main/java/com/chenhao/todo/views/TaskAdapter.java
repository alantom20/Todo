package com.chenhao.todo.views;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chenhao.todo.R;
import com.chenhao.todo.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends  RecyclerView.Adapter<TaskAdapter.TaskHolder>{
    private static final String TAG = TaskAdapter.class.getSimpleName();
    List<Task> tasks = new ArrayList<>();

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks ;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_checkbox,parent,false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskCheckBox.setText(task.getName());
        holder.taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isDone) {
                if(isDone){
                    compoundButton.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    compoundButton.setPaintFlags(0);
                }
            }
        });
        holder.taskCheckBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "onLongClick: " + "test");
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{
        CheckBox taskCheckBox;
        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskCheckBox = itemView.findViewById(R.id.task_check_box);
        }
    }
}
