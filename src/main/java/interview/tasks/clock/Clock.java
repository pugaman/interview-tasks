package interview.tasks.clock;

import java.util.concurrent.TimeUnit;

public interface Clock {

    void schedule(Runnable task, long amount, TimeUnit timeUnit);

    void rewind(long amount, TimeUnit timeUnit);
}
