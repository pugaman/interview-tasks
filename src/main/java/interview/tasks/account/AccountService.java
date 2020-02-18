package interview.tasks.account;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;

@RequiredArgsConstructor
public class AccountService {
    private final Map<Long, Account> accounts;

    public synchronized void transfer(Long fromId, Long toId, long amount) {
        if (fromId.equals(toId)) {
            return;
        }
        final Account from = accounts.get(fromId);
        final Account to = accounts.get(toId);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        databaseDelay();

    }

    @SneakyThrows
    private void databaseDelay() {
        Thread.sleep(10);
    }


}
