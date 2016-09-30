package com.jason.queuedemo.entity;

import android.util.Log;

/**
 * @author zjh
 * @date 2016/9/27
 */
public class TestBean extends TaskBase{
    public TestBean(int priority) {
        super(priority);
    }

    public TestBean(){
        super(0);
    }

    @Override
    public void taskExc() {
        Log.d(TestBean.class.getName(), "tasksuccess,priority==>" + priority);
        excDelayTask();

    }

    private void excDelayTask(){
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
