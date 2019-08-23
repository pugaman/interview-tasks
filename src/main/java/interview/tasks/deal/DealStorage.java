package interview.tasks.deal;

import java.util.Collection;

interface DealStorage {

	void update(Collection<Deal> deals);

	Deal getBy(String name);

	Collection<Deal> getDealsSortedByName();
}
