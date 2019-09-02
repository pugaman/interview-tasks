package interview.tasks.offset;

import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
class Message {
	UUID uuid = UUID.randomUUID();

	LocalDateTime inDateTime = LocalDateTime.now();
	LocalDateTime outDateTime = LocalDateTime.now().plusSeconds(2L);

	String accountNumber = "1234" + "1234" + "1234" + "1234";
	String senderId = "ABCD" + "1234";
	String receiverId = "EFJK" + "5678";
	String pinBlock = "AA12CCFF321234TT00EE00";

	MessageSource source;

	boolean isFrom(@NonNull MessageSource source) {
		return this.source == source;
	}

	static Message ofSource(@NonNull MessageSource source) {
		return new Message(source);
	}
}
