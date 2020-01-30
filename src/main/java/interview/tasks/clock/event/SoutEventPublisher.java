package interview.tasks.clock.event;

public class SoutEventPublisher implements EventPublisher {

    private long startTime = System.currentTimeMillis();

    @Override
    public void publish(Event event) {
        final long time = System.currentTimeMillis() - startTime;
        System.out.println(time + "-" + event);
    }

}
