package com.chenhao.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.chenhao.todo.data.Task;
import com.chenhao.todo.data.TaskUpdate;
import com.chenhao.todo.data.TodoDatabase;
import com.chenhao.todo.fragment.TaskGroupFragment;
import com.chenhao.todo.models.TaskGroup;
import com.chenhao.todo.views.TaskAdapter;
import com.chenhao.todo.views.TaskGroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView name;
    private RecyclerView recyclerViewTaskButton;
    private RecyclerView recyclerViewTasks;
    private FloatingActionButton add;
    private int REQUEST_ADD = 100;
    public static TaskAdapter taskAdapter;
    public static TaskGroupAdapter taskGroupAdapter;
    public static DrawerLayout drawer;
    private FloatingActionButton toc;
    private ImageView avatar;



    private static String groupTitle = "待辦事項";


    public static List<Task> tasks = new ArrayList<>();
    public static List<TaskGroup> taskGroups = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,REQUEST_ADD);
            }
        });
        toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.open();
            }
        });


    }




    private void findViews(){
        name = findViewById(R.id.text_name);

        recyclerViewTasks = findViewById(R.id.recycler_tasks);
        recyclerViewTasks.setHasFixedSize(true);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewTaskButton = findViewById(R.id.recycler_tasks_but);
        recyclerViewTaskButton.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setStackFromEnd(false);
        recyclerViewTaskButton.setLayoutManager(linearLayoutManager);

        new Thread(new Runnable() {
            @Override
            public void run() {
               /* TodoDatabase.getInstance(MainActivity.this).taskDao().insert(new Task("看電影",groupTitle));
                TodoDatabase.getInstance(MainActivity.this).taskDao().insert(new Task("健身房",groupTitle));
                TodoDatabase.getInstance(MainActivity.this).taskDao().insert(new Task("吉他",groupTitle));
                TodoDatabase.getInstance(MainActivity.this).taskDao().insert(new Task("買牙刷","生活"));
                TodoDatabase.getInstance(MainActivity.this).taskDao().insert(new Task("狼人殺","桌遊"));*/

                List<Task> newTasks = TodoDatabase.getInstance(MainActivity.this).taskDao().findByGroup(groupTitle);
                tasks.addAll(newTasks);

                List<TaskGroup> newTaskGroups = TodoDatabase.getInstance(MainActivity.this).taskDao().getTaskGroupAll();
                taskGroups.addAll(newTaskGroups);

                runOnUiThread(new Runnable() {
                    public void run() {
                        taskAdapter = new TaskAdapter(tasks,getApplicationContext());
                        recyclerViewTasks.setAdapter(taskAdapter);

                        taskGroupAdapter = new TaskGroupAdapter(taskGroups,getApplicationContext());
                        recyclerViewTaskButton.setAdapter(taskGroupAdapter);

                    }
                });
            }
        }).start();





        add = findViewById(R.id.fab_add);
        drawer = findViewById(R.id.drawer_layout);
        toc = findViewById(R.id.fab_toc);
        avatar = findViewById(R.id.img_avatar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_ADD){
            if(resultCode == RESULT_OK){
                String taskName = data.getStringExtra("taskName");
                Task task = new Task(taskName,groupTitle);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TodoDatabase.getInstance(MainActivity.this).taskDao().insert(task);
                        List<Task> newTasks = TodoDatabase.getInstance(MainActivity.this).taskDao().findByGroup(groupTitle);
                        List<TaskGroup> newTaskGroups = TodoDatabase.getInstance(MainActivity.this).taskDao().getTaskGroupAll();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                tasks.clear();
                                taskGroups.clear();
                                tasks.addAll(newTasks);
                                taskGroups.addAll(newTaskGroups);
                                taskAdapter.notifyDataSetChanged();
                                taskGroupAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();

            }
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.nav_categories:
                FragmentTransaction transaction = fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        new TaskGroupFragment());
                transaction.commit();
                break;
            case R.id.nav_todo:
                for (Fragment fragment : fragmentManager.getFragments()) {
                    if(fragment != null)
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void updateTaskGroup(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {

                    }
                });
            }
        }).start();
    }

    public static void setGroupTitle(String groupTitle) {
        MainActivity.groupTitle = groupTitle;
    }
}