package com.jason.queuedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jason.queuedemo.entity.TaskBase;
import com.jason.queuedemo.entity.TestBean;
import com.jason.queuedemo.task.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TaskManager taskManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskManager = TaskManager.getInstance();
        taskManager.start();

        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskManager.addTask(getTasks());
            }
        });

        findViewById(R.id.bt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int priority = (int) ((Math.random() * 10) * 10);
                Log.d(MainActivity.class.getName(),"click priority==>"+priority);
                taskManager.addTask(new TestBean(priority));
            }
        });


    }


    public List<TaskBase> getTasks(){
        List<TaskBase> taskBases = new ArrayList<>();
        for (int i=0;i<100;i++){
            int priority = (int) ((Math.random() * 10) * 10);
            taskBases.add(new TestBean(priority));
        }
        return taskBases;
    }
}
