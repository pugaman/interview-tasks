package interview.tasks.report;

import interview.utils.TimeTrackingUtils;
import lombok.extern.java.Log;

import java.util.Random;
import java.util.stream.IntStream;

@Log
public class ReportBuilderTest {

	private static final Random RANDOM = new Random(1_000_000);

	public static void main(String[] args) {
		final ReportBuilder reportBuilder = new ReportBuilder();

		TimeTrackingUtils.executeWithTimeTracking(() -> {
			IntStream.range(1, 5_000_000)
					.map(i -> RANDOM.nextInt())
					.mapToObj(Event::new)
					.forEach(reportBuilder::accumulate);
		}, "Accumulate events");

		TimeTrackingUtils.executeWithTimeTracking(reportBuilder::build, "Build report");
	}
}
