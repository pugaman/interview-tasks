package interview.tasks.clock;

import interview.tasks.clock.event.Event;
import interview.tasks.clock.event.EventPublisher;
import lombok.RequiredArgsConstructor;

import static java.util.concurrent.TimeUnit.SECONDS;

@RequiredArgsConstructor
public class SomeService {

    private final Clock clock;
    private final EventPublisher eventPublisher;

    public void linearMethod() {
        clock.schedule(() -> event("1"), 1, SECONDS);
        clock.schedule(() -> event("2"), 3, SECONDS);
        clock.schedule(() -> event("3"), 5, SECONDS);
    }

    private void event(String s) {
        eventPublisher.publish(new Event(s));
    }

    public void notLinearMethod() {
        clock.schedule(() -> {
            event("1");
            clock.schedule(() -> event("3"), 4, SECONDS);
        }, 1, SECONDS);
        clock.schedule(() -> event("2"), 3, SECONDS);
    }

    public void notLinearMethod2() {
        clock.schedule(() -> {
            event("1");
            clock.schedule(() -> event("3"), 4, SECONDS);
        }, 3, SECONDS);
        clock.schedule(() -> event("2"), 5, SECONDS);
    }

}
