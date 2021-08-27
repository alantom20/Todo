package com.chenhao.todo.data;



import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import androidx.room.Query;
import androidx.room.Update;

import com.chenhao.todo.models.TaskGroup;



import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Query("select * from Task")
    List<Task> getAll();

    @Query("select * from Task WHERE taskGroup = :group ORDER BY date DESC")
    List<Task> findByGroup(String group);

    @Query("select taskGroup,COUNT(taskGroup) AS total , SUM(CASE WHEN isDone = 1 THEN 1 ELSE 0 END) AS isDone from Task GROUP BY taskGroup ")
    List<TaskGroup> getTaskGroupAll();

    @Update(entity = Task.class)
    void updateTask(TaskUpdate taskUpdate);
}




