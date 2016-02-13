/**
 *
 */
package jp.mydns.liquor.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class JCsv {
	private ArrayList<JCsvRow> rows = new ArrayList<JCsvRow>();
	private int maxRowSize = 0;

	/**
	 * CSVテキストの読み込み
	 * @param reader テキストファイルリーダー
	 * @return
	 */
	public boolean read(Reader reader) {
		boolean result = true;
		for (;;) {
			JCsvRow row = JCsvRow.parse(reader);
			if (row == null) break;
			if (maxRowSize < row.size()) {
				maxRowSize = row.size();
			}
			rows.add(row);
		}
		return result;
	}

	/**
	 * CSVファイルの読み込み
	 * @param filename ファイル名
	 * @return
	 */
	public boolean read(String filename) {
		boolean result = false;
		try {
			Reader reader = new FileReader(filename);
			try {
				result = read(reader);
			} finally {
				reader.close();
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}

	public int size() {
		return rows.size();
	}

	public int maxRowSize() {
		return maxRowSize;
	}

	public ArrayList<JCsvRow> rows() {
		return rows;
	}

	public JCsvRow getRow(int rowIndex) {
		return rows.get(rowIndex);
	}

	public String get(int rowIndex, int colIndex) {
		return rows.get(rowIndex).get(colIndex);
	}

	public void set(int rowIndex, int colIndex, String text) {
		rows.get(rowIndex).set(colIndex, text);
	}

	public boolean write(Writer writer) {
		boolean result = true;
		for (JCsvRow row : rows) {
			row.write(writer);
		}
		return result;
	}

	public boolean write(String filename) {
		boolean result = false;
		try {
			Writer writer = new FileWriter(filename);
			try {
				result = write(writer);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			result = false;
		}
		return result;
	}

}
