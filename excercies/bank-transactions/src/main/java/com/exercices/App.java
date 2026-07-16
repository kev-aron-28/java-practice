package com.exercices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ThreadFactory factory = (task) -> {
            Thread thread = new Thread(task);
            thread.setUncaughtExceptionHandler((t, e) -> {
                System.out.printf("[%s] THREAD EXCEPTION:  %s%n", t.getName(), e.getMessage());
            });
            return thread;
        };

        ExecutorService pool = Executors.newFixedThreadPool(10, factory);
    
        pool.awaitTermination(10L, TimeUnit.SECONDS);
    }
}
