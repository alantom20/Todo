package com.chenhao.todo.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;


@Entity
public class TaskUpdate {
    @ColumnInfo(name = "id")
    public long id;
    @ColumnInfo(name = "isDone")
    private boolean isDone;


    public TaskUpdate(long id, boolean isDone) {
        this.id = id;
        this.isDone = isDone;
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
