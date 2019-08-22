package interview.tasks.mandate;

import java.util.HashMap;
import java.util.Map;

class MandateService {

	private Map<String, Boolean> mandates = new HashMap<>();

	void init(Map<String, Boolean> mandates) {
		this.mandates = mandates;
	}

	Boolean isActive(String mandate) {
		return mandates.get(mandate);
	}

}
