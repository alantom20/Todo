package com.chenhao.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class AddActivity extends AppCompatActivity {

    private static final String TAG = AddActivity.class.getSimpleName();
    private FloatingActionButton cancel;
    private EditText edTask;
    private Button add;
    private String taskName;
    private boolean isGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        isGroup = getIntent().getBooleanExtra("ADD_TASK_GROUP",false);
        findViews();


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                taskName = charSequence.toString().trim();
                Log.d(TAG, "onTextChanged: " + taskName.length());
                if(taskName.length()==0){
                    add.setEnabled(false);
                } else {
                    add.setEnabled(true);
                }
                Log.d(TAG, "onTextChanged: "  + taskName);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        add.setEnabled(false);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isGroup){
                    setResult(RESULT_OK);
                    finish();
                }else{
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("taskName",taskName);
                    setResult(RESULT_OK,resultIntent);
                    finish();

                }

            }
        });
    }

    private void findViews(){
        edTask  = findViewById(R.id.ed_task);
        cancel = findViewById(R.id.fab_cancel);
        add = findViewById(R.id.button_add);

        if(isGroup){
            edTask.setHint("Enter new group");
            add.setText("New group ⌅");
        }
    }
}