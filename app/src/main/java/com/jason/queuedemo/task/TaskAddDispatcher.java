package com.jason.queuedemo.task;


import com.jason.queuedemo.entity.TaskBase;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zjh on 2016/9/26.
 */
public class TaskAddDispatcher extends Thread {
    private List<TaskBase> mWaitList;
    private BlockingQueue<TaskBase> mTaskQueue;

    public TaskAddDispatcher(List<TaskBase> waitList, BlockingQueue<TaskBase> taskQueue) {
        mWaitList = waitList;
        mTaskQueue = taskQueue;
    }

    @Override
    public void run() {
        if (mWaitList == null) return;
        while (true) {
            if (!mWaitList.isEmpty() && mTaskQueue != null) {
                synchronized (mWaitList) {
                    mTaskQueue.add(mWaitList.remove(0));
                }
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
