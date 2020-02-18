package interview.tasks.account;

import interview.utils.TimeTrackingUtils;
import lombok.Value;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.LongStream.range;

public class AccountPerformanceTest {

    public static final int ID_MAX = 20;

    public static void main(String[] args) {
        final Map<Long, Account> accounts = getAccounts();
        final AccountService accountService = new AccountService(accounts);

        TimeTrackingUtils.executeWithTimeTracking(() ->
                getAccountTransferCredentials()
                        .parallel()
                        .forEach(ac -> accountService.transfer(ac.getFrom(), ac.getTo(), 10))
        );
    }

    private static Map<Long, Account> getAccounts() {
        return range(1L, ID_MAX)
                .mapToObj(Account::new)
                .collect(Collectors.toMap(
                        Account::getId,
                        Function.identity()));
    }

    private static Stream<AccountCredentials> getAccountTransferCredentials() {
        return range(1, ID_MAX).boxed()
                .flatMap(id1 -> range(1, ID_MAX)
                        .mapToObj(id2 -> new AccountCredentials(id1, id2)));
    }

    @Value
    private static class AccountCredentials {
        Long from;
        Long to;
    }
}
