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
        clock.schedule(() -> eventPublisher.publish(new Event("event1")), 1, SECONDS);
        clock.schedule(() -> eventPublisher.publish(new Event("event2")), 3, SECONDS);
        clock.schedule(() -> eventPublisher.publish(new Event("event3")), 5, SECONDS);
    }

    public void notLinearMethod() {
        clock.schedule(this::subMethod1, 1, SECONDS);
        clock.schedule(() -> eventPublisher.publish(new Event("event2")), 3, SECONDS);
    }

    private void subMethod1() {
        eventPublisher.publish(new Event("event1"));
        clock.schedule(() -> eventPublisher.publish(new Event("event3")), 4, SECONDS);
    }

}
