package interview.tasks.account;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Account {
    private final Long id;
    private long amount;

}
