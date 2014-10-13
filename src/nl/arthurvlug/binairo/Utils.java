package nl.arthurvlug.binairo;

public class Utils {
	public static void flushSleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
	}

	public static void stepSleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}
}
