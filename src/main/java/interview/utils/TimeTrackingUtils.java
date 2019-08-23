package interview.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Log
@UtilityClass
public class TimeTrackingUtils {

	public static void executeWithTimeTracking(Runnable functionToExecute) {
		executeWithTimeTracking(functionToExecute, "");
	}


	public static void executeWithTimeTracking(Runnable functionToExecute, String description) {
		final long start = System.nanoTime();

		functionToExecute.run();

		final long passed = NANOSECONDS.toMillis(System.nanoTime() - start);
		log.info(description + " Time passed in millis: " + passed);
	}

}
