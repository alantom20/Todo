package com.chenhao.todo.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
public class Task {


    @NotNull
    private String name;
    private boolean isDone = false;
    private long date;
    @NotNull
    private String taskGroup;
    @NotNull
    @PrimaryKey(autoGenerate = true)
    public long id = 0;



    public Task(String name, String taskGroup) {
        Date date = new Date();
        this.name = name;
        this.taskGroup = taskGroup;
        this.date = date.getTime();
    }

    @NotNull
    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(@NotNull String taskGroup) {
        this.taskGroup = taskGroup;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}




