package net.tomoyamkung.opencsv.sample04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

public class HeaderColumnNameMappingStrategyTest {
	
	private Reader reader;
	private HeaderColumnNameMappingStrategy<Book> strategy;
	
	@Before
	public void setUp() throws Exception {
		strategy = new HeaderColumnNameMappingStrategy<Book>();
		strategy.setType(Book.class);
		
		reader = new InputStreamReader(
				HeaderColumnNameMappingStrategyTest.class.getClassLoader().getResourceAsStream(
						HeaderColumnNameMappingStrategyTest.class.getSimpleName() + ".csv"), "UTF-8");
	}
	
	@After
	public void tearDown() throws Exception {
		if(reader != null) {
			reader.close();
		}
	}

	/**
	 * HeaderColumnNameMappingStrategy を使って CSV を Bean に変換する。
	 * 
	 * HeaderColumnNameMappingStrategy は次の条件に一致するときに使用します。
	 * 
	 * <ul>
	 * <li>読み込む CSV の1行目がヘッダになっていること</li>
	 * <li>ヘッダに記述されている名称と、マッピングする Bean のフィールド名が一致していること</li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	@Test
	public void HeaderColumnNameMappingStrategyを使ってCSVをBeanに変換する() throws Exception {
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
