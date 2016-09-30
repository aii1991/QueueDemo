package com.jason.queuedemo.task;


import com.jason.queuedemo.entity.TaskBase;

import java.util.concurrent.BlockingQueue;

/**
 * Created by zjh on 2016/9/26.
 */
public class TaskDispatcher extends Thread{
    private BlockingQueue<TaskBase> mTaskQueue;

    public TaskDispatcher(BlockingQueue<TaskBase> taskQueue){
        mTaskQueue = taskQueue;
    }


    @Override
    public void run() {
        while (true){
            try {
                if (mTaskQueue != null){
                    TaskBase task = mTaskQueue.take();
                    task.taskExc();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
