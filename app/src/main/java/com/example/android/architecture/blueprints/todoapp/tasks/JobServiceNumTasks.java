package com.example.android.architecture.blueprints.todoapp.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.example.android.architecture.blueprints.todoapp.data.Task;

import java.util.List;

/**
 * Created by Yonatan Bagizada on 2019-05-02.
 */
public class JobServiceNumTasks extends JobIntentService {

    private static final int JOB_ID = 3489;
    private Handler mHandler;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, JobServiceNumTasks.class, JOB_ID, intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        List<Task> tasks = (List<Task>) intent.getSerializableExtra("ListClass");
        int numTasks = 0;
        for (Task task : tasks){
            if (task.isActive()){
                numTasks++;
            }
        }
        Log.i("Number tasks: ",String.valueOf(numTasks));
        final int finalNumTasks = numTasks;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(JobServiceNumTasks.this,"Number of active tasks: " + finalNumTasks, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
