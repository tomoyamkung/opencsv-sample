package net.tomoyamkung.opencsv.sample05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.tomoyamkung.opencsv.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

public class HeaderColumnNameTranslateMappingStrategyTest {

	private Reader reader;
	private HeaderColumnNameTranslateMappingStrategy<Book> strategy;
	
	@Before
	public void setUp() throws Exception {
		strategy = new HeaderColumnNameTranslateMappingStrategy<Book>();
		strategy.setType(Book.class);
		strategy.setColumnMapping(ヘッダに記述されている名称とBeanのフィールドを紐付けるMapを生成する());
			// ヘッダの名称とフィールドのマッピングを定義した Map オブジェクトを設定する
		
		reader = new InputStreamReader(
				HeaderColumnNameTranslateMappingStrategy.class.getClassLoader().getResourceAsStream(
						HeaderColumnNameTranslateMappingStrategy.class.getSimpleName() + ".csv"), "UTF-8");
	}

	/**
	 * ヘッダに記述されている名称と Bean のフィールド名を紐付ける Map オブジェクトを生成する。
	 * 
	 * 紐付けの関係は次の通り。
	 * 
	 * <ul>
	 * <li>「タイトル」 → Book クラスの "title" フィールド</li>
	 * <li>「出版社」 → Book クラスの "publisher" フィールド</li>
	 * <li>「ページ数」 → Book クラスの "numberOfPages" フィールド</li>
	 * </ul>
	 * 
	 * @return
	 */
	private Map<String, String> ヘッダに記述されている名称とBeanのフィールドを紐付けるMapを生成する() {
		Map<String, String> columnMapping = new HashMap<String, String>();
		columnMapping.put("タイトル", "title");
		columnMapping.put("出版社", "publisher");
		columnMapping.put("ページ数", "numberOfPages");
		return columnMapping;
	}
	
	@After
	public void tearDown() throws Exception {
		if(reader != null) {
			reader.close();
		}
	}
	
	/**
	 * HeaderColumnNameTranslateMappingStrategy を使って CSV を Bean に変換する。
	 * 
	 * HeaderColumnNameTranslateMappingStrategy は次の条件に一致するときに使用します。
	 * 
	 * <ol>
	 * <li>読み込む CSV の1行目がヘッダになっていること</li>
	 * <li>ヘッダに記述されている名称と、マッピングする Bean のフィールド名が一致していないこと</li>
	 * </ol>
	 * 
	 * ヘッダに記述されている名称と、マッピングする Bean のフィールド名を紐付けるために Map<String, String> に定義します。
	 * 
	 * @throws Exception
	 */
	@Test
	public void HeaderColumnNameTranslateMappingStrategyを使ってCSVをBeanに変換する() throws Exception {
		CsvToBean<Book> sut = new CsvToBean<Book>();
		List<Book> actual = sut.parse(strategy, reader);
		
		assertThat(actual.size(), is(3));
		
		Book book = actual.get(0);
		assertThat(book.getTitle(), is("タイトル1"));
		assertThat(book.getPublisher(), is("出版社A"));
		assertThat(book.getNumberOfPages(), is(298));
		
		book = actual.get(1);
		assertThat(book.getTitle(), is("タイトル2"));
		assertThat(book.getPublisher(), is("出版社B"));
		assertThat(book.getNumberOfPages(), is(2980));
		
		book = actual.get(2);
		assertThat(book.getTitle(), is("タイトル3"));
		assertThat(book.getPublisher(), is("出版社C"));
		assertThat(book.getNumberOfPages(), is(29800));
	}

}
