package interview.tasks.clock;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import interview.tasks.clock.event.SoutEventPublisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
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

    @BeforeEach
    public void init() {
        clock = new RewindableClock();
        eventListener = new ArrayListEventPublisher();
        service = new SomeService(clock, eventListener);
    }

    @AfterEach
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