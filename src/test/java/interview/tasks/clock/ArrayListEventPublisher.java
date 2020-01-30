package interview.tasks.clock;

import interview.tasks.clock.event.Event;
import interview.tasks.clock.event.EventPublisher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

class ArrayListEventPublisher implements EventPublisher {

    private List<Event> events = new ArrayList<>();

    @Override
    public void publish(Event event) {
        events.add(event);
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    Event pop() {
        assertFalse(events.isEmpty());
        return events.remove(0);
    }
}
