package com.chenhao.todo.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class},version = 1,exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    private static TodoDatabase instance = null;
    public static TodoDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,TodoDatabase.class,"todo.db").build();
        }
        return instance;
    }
}
