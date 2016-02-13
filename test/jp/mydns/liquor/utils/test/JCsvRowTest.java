package jp.mydns.liquor.utils.test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import jp.mydns.liquor.utils.JCsvRow;
import junit.framework.TestCase;

public class JCsvRowTest extends TestCase {

	@Test
	public void testParseEmpty() {
		StringReader reader = new StringReader("");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNull(row);
	}

	@Test
	public void testParseEmptyLine() {
		StringReader reader = new StringReader("\n");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		assertEquals(0, row.size());
	}

	@Test
	public void testParseSymple() {
		StringReader reader = new StringReader("ABC,DEF,GHI");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		assertEquals(3, row.size());
		assertEquals("ABC", row.get(0));
		assertEquals("DEF", row.get(1));
		assertEquals("GHI", row.get(2));
	}

	@Test
	public void testParseSympleLine() {
		StringReader reader = new StringReader("ABC,DEF,GHI\n");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		assertEquals(3, row.size());
		assertEquals("ABC", row.get(0));
		assertEquals("DEF", row.get(1));
		assertEquals("GHI", row.get(2));
	}

	@Test
	public void testParseSympleSpace() {
		StringReader reader = new StringReader(" AB C, D E F ,GHI \n");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		assertEquals(3, row.size());
		assertEquals("AB C", row.get(0));
		assertEquals("D E F ", row.get(1));
		assertEquals("GHI ", row.get(2));
	}

	@Test
	public void testParseQuote() {
		StringReader reader = new StringReader("\"A BC\",  \"DEF\"\t, \"G\"\"HI\", \"あいう\"\n");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		assertEquals(4, row.size());
		assertEquals("A BC", row.get(0));
		assertEquals("DEF", row.get(1));
		assertEquals("G\"HI", row.get(2));
		assertEquals("あいう", row.get(3));
	}

	@Test
	public void testSet() {
		StringReader reader = new StringReader("ABC,DEF,GHI");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		row.set(3, new String("あいう"));
		assertEquals(4, row.size());
		assertEquals("ABC", row.get(0));
		assertEquals("DEF", row.get(1));
		assertEquals("GHI", row.get(2));
		assertEquals("あいう", row.get(3));
	}

	@Test
	public void testSetSkip() {
		StringReader reader = new StringReader("ABC,DEF,GHI");
		JCsvRow row = JCsvRow.parse(reader);
		reader.close();
		assertNotNull(row);
		row.set(4, new String("あいう"));
		assertEquals(5, row.size());
		assertEquals("ABC", row.get(0));
		assertEquals("DEF", row.get(1));
		assertEquals("GHI", row.get(2));
		assertEquals("", row.get(3));
		assertEquals("あいう", row.get(4));
	}

	@Test
	public void testWriteSymple() {
		JCsvRow row = new JCsvRow();
		row.add("ABC");
		row.add("DEF");
		row.add("GHI");
		StringWriter writer = new StringWriter();
		row.write(writer);
		assertEquals("ABC,DEF,GHI", writer.toString());
		try {
			writer.close();
		} catch (IOException e) {
			//
		}
	}

	@Test
	public void testWriteQuote() {
		JCsvRow row = new JCsvRow();
		row.add("AB,C");
		row.add("DE\"F");
		row.add("GHI");
		StringWriter writer = new StringWriter();
		row.write(writer);
		assertEquals("\"AB,C\",\"DE\"\"F\",GHI", writer.toString());
		try {
			writer.close();
		} catch (IOException e) {
			//
		}
	}

	@Test
	public void testWriteQuote2() {
		JCsvRow row = new JCsvRow();
		row.add("AB,C");
		row.add("DE\"F");
		row.add("G\"H\"I");
		StringWriter writer = new StringWriter();
		row.write(writer);
		assertEquals("\"AB,C\",\"DE\"\"F\",\"G\"\"H\"\"I\"", writer.toString());
		try {
			writer.close();
		} catch (IOException e) {
			//
		}
	}

}
