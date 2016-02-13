/**
 *
 */
package jp.mydns.liquor.utils.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jp.mydns.liquor.utils.JCsv;
import junit.framework.TestCase;

/**
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class JCsvTest extends TestCase {

	/* (非 Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (非 Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		JCsv csv = new JCsv();
		csv.read("work/useragent.csv");
		assertEquals(475, csv.size());
		assertEquals(6, csv.maxRowSize());
	}

}
