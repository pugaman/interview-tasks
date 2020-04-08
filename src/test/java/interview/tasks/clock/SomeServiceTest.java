package interview.tasks.clock;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import interview.tasks.clock.event.SoutEventPublisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SomeServiceTest {

    private SomeService service;
    private Clock clock;
    private ArrayListEventPublisher eventListener;

    private String pop() {
        return eventListener.pop().getData();
    }

    private String popExactlyOne() {
        return eventListener.popExactlyOne().getData();
    }

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

        clock.rewind(2, SECONDS);
        assertEquals("1", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("2", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("3", popExactlyOne());
    }


    @Test
    public void simpleLogicMethodTest2() {
        service.linearMethod();

        clock.rewind(10, SECONDS);
        assertEquals("1", pop());
        assertEquals("2", pop());
        assertEquals("3", pop());

    }

    @Test
    public void notLinealMethodTest() {
        service.notLinearMethod();

        clock.rewind(2, SECONDS);
        assertEquals("1", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("2", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("3", popExactlyOne());
    }

    @Test
    public void notLinealMethodTest2() {
        service.notLinearMethod();

        clock.rewind(15, SECONDS);
        assertEquals("1", pop());
        assertEquals("2", pop());
        assertEquals("3", pop());
    }

    @Test
    public void notLinearMethod2Test1() {
        service.notLinearMethod2();

        clock.rewind(4, SECONDS);
        assertEquals("1", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("2", popExactlyOne());

        clock.rewind(2, SECONDS);
        assertEquals("3", popExactlyOne());
    }

//    @Test
    public void test1() throws InterruptedException {
        new SomeService(new ExecutorClock(), new SoutEventPublisher()).linearMethod();
        Thread.sleep(10_000);
    }

}