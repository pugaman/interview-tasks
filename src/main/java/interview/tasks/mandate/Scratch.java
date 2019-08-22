package interview.tasks.mandate;

import lombok.extern.java.Log;

import java.util.Collections;

@Log
public class Scratch {

	public static void main(String[] args) {
		final MandateService gm = new MandateService();
		gm.init(Collections.singletonMap("GM_SOMETHING_2017.1_0", true));

		if (gm.isActive("GM_SOMETHING_2017.1_0")) {
			doNew();
		} else {
			doOld();
		}

		doOther();

		if (gm.isActive("GM_SOMETHING_2017.1_0")) {
			doNew();
		} else {
			doOld();
		}

		doOther();

		if (gm.isActive("GM_SOMETHING_2017.1_0")) {
			doOld();
		} else {
			doNew();
		}
	}

	private static void doOld() {
		log.info("Do old behaviour");
	}

	private static void doNew() {
		log.info("Do new behaviour");
	}

	private static void doOther() {
		log.info("Do other behaviour");
	}
}
