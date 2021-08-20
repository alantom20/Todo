package com.chenhao.todo.models;


public class TaskGroup {
    int taskCount;
    String title;
    int taskProgress;

    public TaskGroup(int taskCount, String title, int taskProgress) {
        this.taskCount = taskCount;
        this.title = title;
        this.taskProgress = taskProgress;
    }



    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(int taskProgress) {
        this.taskProgress = taskProgress;
    }



}
