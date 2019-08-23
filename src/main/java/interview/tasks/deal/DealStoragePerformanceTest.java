package interview.tasks.deal;

import interview.utils.TimeTrackingUtils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class DealStoragePerformanceTest {

	public static void main(String[] args) {
		final DealStorage dealStorage = new DealInMemoryStorage();

		TimeTrackingUtils.executeWithTimeTracking(
				() -> dealStorage.update(
						IntStream.range(1, 10_000)
								.mapToObj(i -> Deal.generate())
								.collect(Collectors.toList())),
				"Update storage"
		);

		TimeTrackingUtils.executeWithTimeTracking(
				() -> IntStream.range(1, 1_000).forEach(i -> apiCall(dealStorage)),
				"Fetch storage"
		);
	}

	private static void apiCall(DealStorage dealStorage) {
		dealStorage.getDealsSortedByName();
		dealStorage.getBy("");
		dealStorage.getBy("11");
		dealStorage.getBy("123");
	}
}
