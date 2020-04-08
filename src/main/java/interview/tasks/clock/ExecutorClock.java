package interview.tasks.clock;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorClock implements Clock {

    private final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(4);

    @Override
    public void schedule(Runnable task, long amount, TimeUnit timeUnit) {
        executor.schedule(task, amount, timeUnit);
    }

    @Override
    public void rewind(long amount, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.toMillis(amount));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
