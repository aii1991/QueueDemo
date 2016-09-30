package com.jason.queuedemo.task;


import com.jason.queuedemo.entity.TaskBase;

import java.util.List;

/**
 * Created by zjh on 2016/9/26.
 */
public class TaskManager {
    public final int THREAD_SIZE = 3;

    private static TaskManager mTaskManager;
    private TaskQueue mTaskQueue;

    private TaskManager(){
        mTaskQueue = new TaskQueue(THREAD_SIZE);
    }

    public synchronized static TaskManager getInstance(){
        if (mTaskManager == null){
            mTaskManager = new TaskManager();
        }
        return mTaskManager;
    }

    public boolean addTask(TaskBase taskBase){
        return mTaskQueue.addTask(taskBase);
    }

    public boolean addTask(List<TaskBase> taskBases){
        return mTaskQueue.addTask(taskBases);
    }

    public boolean retryTask(TaskBase taskBase){
        return mTaskQueue.retry(taskBase);
    }

    public boolean cancelTask(TaskBase taskBase){
        return mTaskQueue.remove(taskBase);
    }

    public void start(){
        mTaskQueue.start();
    }

    public void stop(){
        mTaskQueue.stop();
    }

}
