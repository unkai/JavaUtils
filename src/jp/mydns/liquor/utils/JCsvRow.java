/**
 *
 */
package jp.mydns.liquor.utils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * CSVファイルの行オブジェクト
 *
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class JCsvRow {
	/**
	 * テキスト行を解析してCSVの行オブジェクトを生成する。
	 * @param line
	 * @return
	 */
	static public JCsvRow parse(Reader reader) {
		JCsvRow row = null;
		try {
			int st = 0;
			StringBuffer buffer = null;
			while (st >= 0) {
				int ch = reader.read();
				switch (st) {
				case 0:
					switch (ch) {
					case ',':
						if (row == null) row = new JCsvRow();
						row.add(new String(""));
						buffer = null;
						st = 0;
						break;
					case '"':
						if (row == null) row = new JCsvRow();
						buffer = new StringBuffer();
						st = 1;
						break;
					case ' ':
						buffer = null;
						st = 0;
						break;
					case '\t':
						buffer = null;
						st = 0;
						break;
					case '\r':
						buffer = null;
						st = 0;
						break;
					case '\n':
						if (row == null) row = new JCsvRow();
						st = -1;
						break;
					case -1:
						st = -1;
						break;
					default:
						if (row == null) row = new JCsvRow();
						buffer = new StringBuffer();
						buffer.append((char) ch);
						st = 4;
						break;
					}
					break;
				case 1:
					switch (ch) {
					case ',':
						buffer.append((char) ch);
						st = 1;
						break;
					case '"':
						st = 2;
						break;
					case ' ':
						buffer.append((char) ch);
						st = 1;
						break;
					case '\t':
						st = 1;
						break;
					case '\r':
						st = -2;
						break;
					case '\n':
						st = -2;
						break;
					case -1:
						st = -2;
						break;
					default:
						buffer.append((char) ch);
						st = 1;
						break;
					}
					break;
				case 2:
					switch (ch) {
					case ',':
						row.add(buffer.toString());
						buffer = null;
						st = 0;
						break;
					case '"':
						buffer.append((char) ch);
						st = 1;
						break;
					case ' ':
						row.add(buffer.toString());
						buffer = null;
						st = 3;
						break;
					case '\t':
						row.add(buffer.toString());
						buffer = null;
						st = 3;
						break;
					case '\r':
						row.add(buffer.toString());
						buffer = null;
						st = 3;
						break;
					case '\n':
						row.add(buffer.toString());
						buffer = null;
						st = -1;
						break;
					case -1:
						st = -1;
						break;
					default:
						st = -2;
						break;
					}
					break;
				case 3:
					switch (ch) {
					case ',':
						st = 0;
						break;
					case '"':
						st = -2;
						break;
					case ' ':
						st = 3;
						break;
					case '\t':
						st = 3;
						break;
					case '\r':
						st = 3;
						break;
					case '\n':
						st = -1;
						break;
					case -1:
						st = -1;
						break;
					default:
						st = -2;
						break;
					}
					break;
				case 4:
					switch (ch) {
					case ',':
						row.add(buffer.toString());
						buffer = null;
						st = 0;
						break;
					case '"':
						st = -2;
						break;
					case ' ':
						buffer.append((char) ch);
						st = 4;
						break;
					case '\t':
						st = 4;
						break;
					case '\r':
						st = 4;
						break;
					case '\n':
						row.add(buffer.toString());
						buffer = null;
						st = -1;
						break;
					case -1:
						row.add(buffer.toString());
						buffer = null;
						st = -1;
						break;
					default:
						buffer.append((char) ch);
						st = 4;
						break;
					}
					break;
				}
			}
		} catch (IOException e) {
			return null;
		}
		return row;
	}

	/**
	 * CSVの行のカラムデータを格納する文字列のリスト
	 */
	private ArrayList<String> columns = new ArrayList<String>();

	/**
	 * 行のカラム数
	 * @return カラム数
	 */
	public int size() {
		return columns.size();
	}

	/**
	 * カラムを追加する。
	 * @param text
	 */
	public void add(String text) {
		columns.add(text);
	}

	/**
	 * カラムを取得する
	 * @param index カラムの位置
	 * @return カラムの文字列
	 */
	public String get(int index) {
		if (index < columns.size()) {
			return columns.get(index);
		} else {
			return null;
		}
	}

	/**
	 * カラムに文字列を設定する
	 * @param index カラムの位置
	 * @param text 設定する文字列
	 */
	public void set(int index, String text) {
		while (index >= columns.size()) {
			columns.add("");
		}
		columns.set(index, text);
	}

	/**
	 * CSVテキストの出力
	 * @param writer 出力先
	 */
	public void write(Writer writer) {
		try {
			boolean first = true;
			for (String text : columns) {
				if (!first) {
					writer.write(',');
				}
				first = false;
				if (text.indexOf('"') >= 0) {
					writer.write('"');
					int s = 0;
					while (s < text.length()) {
						int e = text.indexOf('"', s);
						if (e < 0) {
							writer.write(text.substring(s));
							break;
						}
						writer.write(text.substring(s, e));
						writer.write('"');
						writer.write('"');
						s = e + 1;
					}
					writer.write('"');
				} else if (text.indexOf(',') >= 0) {
					writer.write('"');
					writer.write(text);
					writer.write('"');
				} else {
					writer.write(text);
				}
			}
		} catch (IOException e) {
			//
		}
	}
}
