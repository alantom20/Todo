package com.chenhao.todo.views;

import android.content.Context;
import android.graphics.Paint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.chenhao.todo.MainActivity;
import com.chenhao.todo.R;
import com.chenhao.todo.data.Task;
import com.chenhao.todo.data.TaskUpdate;
import com.chenhao.todo.data.TodoDatabase;
import com.chenhao.todo.models.TaskGroup;


import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.chenhao.todo.MainActivity.taskAdapter;
import static com.chenhao.todo.MainActivity.taskGroupAdapter;
import static com.chenhao.todo.MainActivity.taskGroups;


public class TaskAdapter extends  RecyclerView.Adapter<TaskAdapter.TaskHolder>{
    private static final String TAG = TaskAdapter.class.getSimpleName();

    private List<Task> tasks;
    private Context context;
    public TaskAdapter(List<Task> tasks,Context context) {
        this.tasks = tasks;
        this.context = context;
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
        holder.taskCheckBox.setChecked(task.isDone());
        if(task.isDone()){
            holder.taskCheckBox.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.taskCheckBox.setPaintFlags(0);
        }

        holder.taskCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDone = ((CheckBox) view).isChecked();
                if(isDone){
                    holder.taskCheckBox.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    holder.taskCheckBox.setPaintFlags(0);
                }
                holder.taskCheckBox.setChecked(isDone);
                tasks.get(position).setDone(isDone);
                CountDownLatch cdt = new CountDownLatch(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TaskUpdate taskUpdate = new TaskUpdate(task.getId(),task.isDone());
                        TodoDatabase.getInstance(context).taskDao().updateTask(taskUpdate);
                        List<TaskGroup> newTaskGroups = TodoDatabase.getInstance(context).taskDao().getTaskGroupAll();
                        taskGroups.clear();
                        taskGroups.addAll(newTaskGroups);
                        cdt.countDown();
                    }
                }).start();
                try {
                    cdt.await();
                    taskGroupAdapter.notifyDataSetChanged();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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
