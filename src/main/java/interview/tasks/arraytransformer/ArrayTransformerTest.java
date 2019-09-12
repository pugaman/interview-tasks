package interview.tasks.arraytransformer;

import interview.utils.TimeTrackingUtils;

import java.util.Random;
import java.util.stream.IntStream;

public class ArrayTransformerTest {

	private static final Random RANDOM = new Random();

	private static final int[] SOURCE = IntStream.range(0, 1_000_000)
			.map(i -> RANDOM.nextInt(3))
			.toArray();

	public static void main(String[] args) {
		calculateTransformOneThread();
	}

	private static void calculateTransformOneThread() {
		final ArrayTransformer arrayTransformer = new OneThreadSourceTransformer();
		TimeTrackingUtils.executeWithTimeTracking(
				() -> arrayTransformer.transform(SOURCE),
				"One thread calculation");
	}
}