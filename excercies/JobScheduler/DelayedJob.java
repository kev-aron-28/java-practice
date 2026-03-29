package excercies.JobScheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedJob implements Delayed {
    private final Job job;
    private final long executeAt;
    private int retries;

    public DelayedJob(Job job, long executeAt, int retries) {
        this.job = job;
        this.executeAt = System.currentTimeMillis() + executeAt;
        this.retries = retries;
    }

    public long getExecuteAt() {
        return executeAt;
    }

    public Job getJob() {
        return job;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(
            this.getDelay(TimeUnit.MILLISECONDS), 
            other.getDelay(TimeUnit.MILLISECONDS)
        );
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = executeAt - System.currentTimeMillis();

        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }  
}
