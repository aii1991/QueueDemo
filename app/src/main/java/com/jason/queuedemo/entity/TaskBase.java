package com.jason.queuedemo.entity;

import java.io.Serializable;

/**
 * Created by zjh on 2016/9/26.
 */
public abstract class TaskBase implements Serializable,Comparable{
    public long taskId;
    public int priority;

    public TaskBase(int priority){
        this.priority = priority;
    }

    public abstract void taskExc();

    @Override
    public int compareTo(Object o) {
        TaskBase taskBase = (TaskBase) o;
        if (priority > taskBase.priority){
            return -1;
        }else if (priority < taskBase.priority){
            return 1;
        }
        return 0;
    }
}
