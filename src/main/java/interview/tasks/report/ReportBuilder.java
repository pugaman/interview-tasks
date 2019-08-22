package interview.tasks.report;

import lombok.extern.java.Log;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Log
class ReportBuilder {

	private final Map<LocalTime, Event> reportData = new HashMap<>();

	void accumulate(Event event) {
		reportData.put(LocalTime.now(), event);
	}

	String build() {
		final StringBuilder result = new StringBuilder();

		reportData.values().forEach(result::append);

		return result.toString();
	}
}
