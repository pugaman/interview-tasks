package interview.tasks.clock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class RewindableClock implements Clock {

    private Map<Long, Runnable> tasks = new HashMap<>();
    private long skipped = 0;

    @Override
    public void schedule(Runnable task, long amount, TimeUnit timeUnit) {
        tasks.put(timeUnit.toNanos(amount), task);
    }

    public void rewind(long amount, TimeUnit timeUnit) {
        final long skipNanos = timeUnit.toNanos(amount);
        tasks.keySet().stream()
                .sorted()
                .filter(t -> t < skipNanos + skipped)
                .map(tasks::remove)
                .forEach(Runnable::run);
        skipped += skipNanos;
    }
}
