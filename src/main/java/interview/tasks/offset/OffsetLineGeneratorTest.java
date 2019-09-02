package interview.tasks.offset;

import interview.utils.TimeTrackingUtils;
import lombok.extern.java.Log;

import java.util.Arrays;

import static interview.tasks.offset.MessageSource.ACQUIRER;

@Log
public class OffsetLineGeneratorTest {

	public static void main(String[] args) {
		final OffsetLineGenerator offsetLineGenerator = new OffsetLineGenerator();

		final Message message = Message.ofSource(ACQUIRER);
		final OffsetVersion version = OffsetVersion.V_1_2;

		TimeTrackingUtils.executeWithTimeTracking(() -> {
			final byte[] offsetLine = offsetLineGenerator.generate(message, version);
			log.info(Arrays.toString(offsetLine));
		});
	}

}
