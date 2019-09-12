package interview.tasks.arraytransformer;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ElementTransformer {

	private static final Random RANDOM = new Random();

	private static final int[] TRANSFORMATION = IntStream.range(0, 5_000)
			.map(i -> RANDOM.nextInt(1000))
			.toArray();

	int transform(int source) {
		return Arrays.stream(TRANSFORMATION).reduce(1, (a, b) -> a + source * b);
	}
}
