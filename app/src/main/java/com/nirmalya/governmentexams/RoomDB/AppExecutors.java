package com.nirmalya.governmentexams.RoomDB;

import android.os.Handler;
import android.os.Looper;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;

public class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;

    private final Executor databaseIO;  // database
    private final Executor mainThread;
    private final Executor networkIO;


    private AppExecutors(Executor databaseIO, Executor networkIO, Executor mainThread)
    {
        this.databaseIO = databaseIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public static AppExecutors getsInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(
                        Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),
                        new MainThreadExecutor()
                        );
            }

        }
        return sInstance;
    }

    public Executor databaseIO() {
        return databaseIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
