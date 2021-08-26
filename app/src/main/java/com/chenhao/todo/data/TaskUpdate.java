package com.chenhao.todo.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity
public class TaskUpdate {
    @ColumnInfo(name = "id")
    public long id;
    @ColumnInfo(name = "isDone")
    private boolean isDone;
    private String name;

    public TaskUpdate(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
