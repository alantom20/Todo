package com.chenhao.todo.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenhao.todo.MainActivity;
import com.chenhao.todo.R;
import com.chenhao.todo.data.Task;
import com.chenhao.todo.data.TodoDatabase;
import com.chenhao.todo.models.TaskGroup;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.chenhao.todo.MainActivity.setGroupTitle;
import static com.chenhao.todo.MainActivity.taskAdapter;
import static com.chenhao.todo.MainActivity.taskGroupAdapter;
import static com.chenhao.todo.MainActivity.tasks;


public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.TaskGroupHolder> {


    private List<TaskGroup> taskGroups;
    private Context context;
    private FragmentManager fragmentManager;

    public TaskGroupAdapter(List<TaskGroup> taskGroups,Context context) {
        this.taskGroups = taskGroups;
        this.context = context;
    }

    public TaskGroupAdapter(List<TaskGroup> taskGroups, Context context, FragmentManager fragmentManager) {
        this.taskGroups = taskGroups;
        this.context = context;
        this.fragmentManager = fragmentManager;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountDownLatch cdt = new CountDownLatch(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        setGroupTitle(taskGroup.getTaskGroup());
                        List<Task> newTasks = TodoDatabase.getInstance(context).taskDao().findByGroup(taskGroup.getTaskGroup());
                        cdt.countDown();
                        tasks.clear();
                        tasks.addAll(newTasks);
                        cdt.countDown();
                    }
                }).start();
                try {
                    cdt.await();
                    taskAdapter.notifyDataSetChanged();
                    if(fragmentManager != null){
                        for (Fragment fragment : fragmentManager.getFragments()) {
                            if(fragment != null)
                                fragmentManager.beginTransaction().remove(fragment).commit();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


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
