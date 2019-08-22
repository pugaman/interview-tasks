package interview.tasks.report;

import lombok.NonNull;
import lombok.Value;

@Value
class Event implements Comparable<Event> {
	private final int payload;

	@Override
	public int compareTo(@NonNull Event e) {
		return Integer.compare(payload, e.payload);
	}
}


