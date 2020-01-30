package interview.tasks.clock;

import interview.tasks.clock.event.SoutEventPublisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SomeServiceTest {

    private SomeService service;
    private RewindableClock clock;
    private ArrayListEventPublisher eventListener;


    @Before
    public void init() {
        clock = new RewindableClock();
        eventListener = new ArrayListEventPublisher();
        service = new SomeService(clock, eventListener);
    }

    @After
    public void finish() {
        assertTrue(eventListener.isEmpty());
    }

    @Test
    public void simpleLogicMethodTest() {
        service.linearMethod();

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event1", eventListener.pop().getData());

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event2", eventListener.pop().getData());

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event3", eventListener.pop().getData());
    }

    @Test
    public void simpleLogicMethodTest2() {
        service.linearMethod();

        clock.rewind(10, TimeUnit.SECONDS);
        assertEquals("event1", eventListener.pop().getData());
        assertEquals("event2", eventListener.pop().getData());
        assertEquals("event3", eventListener.pop().getData());

    }

    @Test
    public void notLinealMethodTest() {
        service.notLinearMethod();

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event1", eventListener.pop().getData());

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event2", eventListener.pop().getData());

        clock.rewind(2, TimeUnit.SECONDS);
        assertEquals("event3", eventListener.pop().getData());
    }

    @Test
    public void notLinealMethodTest2() {
        service.notLinearMethod();

        clock.rewind(15, TimeUnit.SECONDS);
        assertEquals("event1", eventListener.pop().getData());
        assertEquals("event2", eventListener.pop().getData());
        assertEquals("event3", eventListener.pop().getData());
    }

    //    @Test
    public void test1() throws InterruptedException {
        new SomeService(new ExecutorClock(), new SoutEventPublisher()).linearMethod();
        Thread.sleep(10_000);
    }

}