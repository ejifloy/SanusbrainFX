package com.sanusbrain.Utils;

import javafx.concurrent.Task;

public class AsyncTaskRunner {

    /**
     * Executes an asynchronous Task
     *
     * @param task the task to be executed
     * @param <V> return Value of the task
     */
    public static <V> void runAsync(Task<V> task){
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }


    /**
     * Creates and executes an asynchronous task, which performs the defined task
     *
     * @param work the task to be preformed
     * @param <V> the return value
     */
    public static <V> void runAsync(AsyncWork<V> work){
        Task<V> task = new Task<>() {
            @Override
            protected V call() throws Exception {
                return work.doWork();
            }
        };
        runAsync(task);
    }


    /**
     * Interface, that defines the work to be preformed
     *
     * @param <V> return value of the task
     */
    @FunctionalInterface
    public interface AsyncWork<V>{
        V doWork() throws Exception;
    }
}
