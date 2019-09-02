package interview.tasks.offset;

import com.google.common.base.Strings;
import lombok.SneakyThrows;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static interview.tasks.offset.MessageSource.*;
import static interview.tasks.offset.OffsetVersion.V_1_0;
import static interview.tasks.offset.OffsetVersion.V_1_2;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

class OffsetLineGenerator {

	@SneakyThrows
	byte[] generate(Message message, OffsetVersion version) {
		final StringBuilder offsetLine = new StringBuilder();

		if (version.compareTo(V_1_0) >= 0) {
			// Offset 0 Offset Version
			addNumericValue(version.getValue(), 3, offsetLine);

			// Offset 3 Message Id
			String id;
			if (message.isFrom(PLATFORM)) {
				id = message.getUuid().toString();
			} else if (message.isFrom(BACK_OFFICE)) {
				id = message.getSenderId();
			} else {
				id = message.getSenderId() + message.getReceiverId();
			}
			addSymbolicValue(id, 32, offsetLine);

			// Offset 35 Message In Date Time
			addNumericValue(message.getInDateTime().format(ISO_DATE_TIME), 18, offsetLine);

			// Offset 53 Acquirer Id
			String acquirerId;
			if (message.isFrom(ACQUIRER)) {
				acquirerId = message.getSenderId();
			} else {
				acquirerId = message.getReceiverId();
			}
			addSymbolicValue(acquirerId, 16, offsetLine);

			// Offset 69 Issuer Id
			String issuerId;
			if (message.isFrom(ISSUER)) {
				issuerId = message.getSenderId();
			} else {
				issuerId = message.getReceiverId();
			}
			addSymbolicValue(issuerId, 16, offsetLine);

			// Offset 85 Account Number
			addSymbolicValue(message.getAccountNumber(), 16, offsetLine);

			// Offset 101 Sender Id
			addSymbolicValue(message.getSenderId(), 16, offsetLine);
		}

		if (version.compareTo(V_1_2) >= 0) {
			// Offset 117 Receiver Id
			addSymbolicValue(message.getReceiverId(), 16, offsetLine);

			// Offset 133 Message Out Date Time
			addNumericValue(message.getOutDateTime().format(ISO_DATE_TIME), 18, offsetLine);

			// Offset 151 Pin Block
			addSymbolicValue(message.getPinBlock(), 20, offsetLine);
		}

		return offsetLine.toString().getBytes("IBM-1047");
	}

	private static void addNumericValue(String value, int length, StringBuilder offsetLine) {
		String result = value;
		if (result != null) {
			final Matcher m = Pattern.compile("\\D").matcher(result);
			result = m.replaceAll("0");
		} else {
			result = "";
		}

		if (result.length() < length) {
			result = result.substring(0, length);
		} else {
			result = Strings.padStart(result, length, '0');
		}

		offsetLine.append(result);
	}

	private static void addSymbolicValue(String value, int length, StringBuilder offsetLine) {
		String result = value;
		if (result == null) {
			result = "";
		}

		if (result.length() < length) {
			result = Strings.padEnd(result, length, ' ');
		} else {
			result = result.substring(0, length);
		}

		offsetLine.append(result);
	}
}
