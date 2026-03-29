package excercies.JobScheduler;

import java.util.concurrent.DelayQueue;

public class Worker implements Runnable {

    private final DelayQueue<DelayedJob> queue;

    public Worker(DelayQueue<DelayedJob> queue) {
        this.queue = queue;
    }



    @Override
    public void run() {
        while (true) { 
            try {
                DelayedJob job = queue.take();

                try {
                    job.getJob().execute();   
                } catch (Exception e) {
                    
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                break;
            }
        }
    }

    private void handleRetry(DelayedJob job) {
        if(job.getRetries() == 0) return;

        job.setRetries(job.getRetries() - 1);

        queue.put(job);
    }
}
