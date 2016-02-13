/**
 *
 */
package jp.mydns.liquor.checkUserAgent;

import jp.mydns.liquor.utils.UserAgent;

/**
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (String param : args) {
			UserAgent ua = UserAgent.analyze(param);
			System.out.println("model         : " + ua.model());
			System.out.println("os            : " + ua.os());
			System.out.println("osVersion     : " + ua.osVersion());
			System.out.println("browser       : " + ua.browser());
			System.out.println("browserVersion: " + ua.browserVersion());
		}
	}

}
