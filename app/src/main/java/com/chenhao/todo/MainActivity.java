package com.chenhao.todo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chenhao.todo.models.Task;
import com.chenhao.todo.models.TaskGroup;
import com.chenhao.todo.views.TaskAdapter;
import com.chenhao.todo.views.TaskGroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView name;
    private RecyclerView recyclerViewTaskButton;
    private List<TaskGroup> taskButtons = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private RecyclerView recyclerViewTasks;
    private FloatingActionButton add;
    private int REQUEST_ADD = 100;
    private TaskAdapter taskAdapter;
    private TaskGroupAdapter taskButtonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData();
        findViews();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,REQUEST_ADD);
            }
        });
    }

    private void findViews(){
        name = findViewById(R.id.text_name);

        recyclerViewTaskButton = findViewById(R.id.recycler_tasks_but);
        recyclerViewTaskButton.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(false);
        recyclerViewTaskButton.setLayoutManager(linearLayoutManager);
        taskButtonAdapter = new TaskGroupAdapter(taskButtons);
        recyclerViewTaskButton.setAdapter(taskButtonAdapter);

        recyclerViewTasks = findViewById(R.id.recycler_tasks);
        recyclerViewTasks.setHasFixedSize(true);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(tasks);
        recyclerViewTasks.setAdapter(taskAdapter);

        add = findViewById(R.id.fab_add);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ADD){
            if(resultCode == RESULT_OK){
                String taskName = data.getStringExtra("taskName");
                Log.d(TAG, "onActivityResult: " + taskName);
                tasks.add(new Task(taskName));
                taskAdapter.notifyDataSetChanged();

            }
        }

    }

    private void addData(){
        taskButtons.add(new TaskGroup(10,"工作",1));
        tasks.add(new Task("游泳"));
        tasks.add(new Task("打球"));
        tasks.add(new Task("買午餐"));
        tasks.add(new Task("買機票"));
        tasks.add(new Task("體檢"));
    }
}