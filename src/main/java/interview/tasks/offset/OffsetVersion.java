package interview.tasks.offset;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum OffsetVersion {
	V_1_0("1.0"),
	V_1_2("1.2");

	private final String value;
}