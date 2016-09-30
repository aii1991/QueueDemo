package com.jason.queuedemo.task;

import com.jason.queuedemo.entity.TaskBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by zjh on 2016/9/26.
 */
public class TaskQueue {
    private final int QUEUE_SIZE = 20;
    private final List<TaskBase> mWaitList = new ArrayList<TaskBase>();
    private final PriorityBlockingQueue<TaskBase> mTaskQueue =  new PriorityBlockingQueue(QUEUE_SIZE);

    private ExecutorService mThreadPool;
    private ExecutorService mAddThread;
    private final int mThreadSize;

    public TaskQueue(int threadSize){
        mThreadPool = Executors.newFixedThreadPool(threadSize);
        mAddThread = Executors.newSingleThreadExecutor();
        mThreadSize = threadSize;
    }

    public void start(){
        for (int i=0; i<mThreadSize; i++){
            mThreadPool.execute(new TaskDispatcher(mTaskQueue));
        }
        mAddThread.execute(new TaskAddDispatcher(mWaitList,mTaskQueue));
    }

    public void stop(){
        if (mThreadPool != null && !mThreadPool.isShutdown()){
            mThreadPool.shutdown();
        }
    }


    public boolean addTask(TaskBase taskBase){
        synchronized (mWaitList){
            return mWaitList.add(taskBase);
        }
    }

    public boolean addTask(List<TaskBase> taskBases){
        synchronized (mWaitList){
            return mWaitList.addAll(taskBases);
        }
    }

    public boolean retry(TaskBase taskBase){
        synchronized (mWaitList){
            if (mWaitList.contains(taskBase)){
                return false;
            }
            return mWaitList.add(taskBase);
        }
    }

    public boolean remove(TaskBase taskBase){
        synchronized (mWaitList){
            return mWaitList.remove(taskBase);
        }
    }

}
