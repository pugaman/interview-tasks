package interview.tasks.deal;


import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DealInMemoryStorageTest {

    @Test
    @Disabled
    public void should_update() {
        DealInMemoryStorage storage = new DealInMemoryStorage();
        storage.update(List.of(Deal.generate()));
        // some asserts
    }
}