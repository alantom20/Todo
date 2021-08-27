package com.chenhao.todo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chenhao.todo.AddActivity;
import com.chenhao.todo.R;
import com.chenhao.todo.views.TaskAdapter;
import com.chenhao.todo.views.TaskGroupAdapter;
import com.chenhao.todo.views.TaskGroupIconAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



import static com.chenhao.todo.MainActivity.drawer;
import static com.chenhao.todo.MainActivity.taskAdapter;
import static com.chenhao.todo.MainActivity.taskGroupAdapter;
import static com.chenhao.todo.MainActivity.taskGroups;


public class TaskGroupFragment extends Fragment {

    private static final String TAG = TaskGroupFragment.class.getSimpleName();
    private static final int REQUEST_ADD_GROUP = 200;
    private View rootView;
    private RecyclerView recyclerView;
    private FloatingActionButton toc;
    private FloatingActionButton add;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_task_group,container,false);
        findViews();

        toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.open();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), AddActivity.class);
                intent.putExtra("ADD_TASK_GROUP",true);
                startActivityForResult(intent,REQUEST_ADD_GROUP);
            }
        });

        return rootView;


    }

    private void findViews(){
        toc = rootView.findViewById(R.id.fab_toc_fragment);

        recyclerView = rootView.findViewById(R.id.recycler_group);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        TaskGroupAdapter taskGroupAdapter = new TaskGroupAdapter(taskGroups,getContext(),getFragmentManager());
        recyclerView.setAdapter(taskGroupAdapter);

        add = rootView.findViewById(R.id.fab_add_group);

    }




}
