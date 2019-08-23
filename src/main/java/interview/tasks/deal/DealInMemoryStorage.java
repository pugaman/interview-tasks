package interview.tasks.deal;

import java.util.*;
import java.util.stream.Collectors;

public class DealInMemoryStorage implements DealStorage {

	private Map<String, Deal> nameToDeal = new HashMap<>();

	@Override
	public void update(Collection<Deal> deals) {
		nameToDeal = deals.stream().collect(Collectors.toMap(Deal::getName, d -> d, (a, b) -> b, HashMap::new));
	}

	@Override
	public Deal getBy(String name) {
		return nameToDeal.get(name);
	}

	@Override
	public Collection<Deal> getDealsSortedByName() {
		return nameToDeal.values().stream().sorted(Comparator.comparing(Deal::getName)).collect(Collectors.toList());
	}
}
