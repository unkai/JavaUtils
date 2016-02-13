/**
 *
 */
package jp.mydns.liquor.utils.test;

import org.junit.Test;

import jp.mydns.liquor.utils.JCsv;
import jp.mydns.liquor.utils.JCsvRow;
import jp.mydns.liquor.utils.UserAgent;
import junit.framework.TestCase;

/**
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class UserAgentTest extends TestCase {

	/**
	 * {@link jp.mydns.liquor.utils.UserAgent#analyze(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testAnalyze() {
		JCsv csv = new JCsv();
		csv.read("work/useragent.csv");
		int i = 0;
		for (JCsvRow row : csv.rows()) {
			i++;
			System.out.println("index=" + i);
			UserAgent ua = UserAgent.analyze(row.get(5));
			assertEquals(row.get(0), ua.model());
			assertEquals(row.get(1), ua.os());
			assertEquals(row.get(2), ua.osVersion());
			assertEquals(row.get(3), ua.browser());
			assertEquals(row.get(4), ua.browserVersion());
		}
	}

}
