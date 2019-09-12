package interview.tasks.arraytransformer;

import java.util.Arrays;

public class OneThreadSourceTransformer extends AbstractArrayTransformer {

	@Override
	public int[] transform(int[] source) {
		return Arrays.stream(source).map(elementTransformer::transform).toArray();
	}
}
