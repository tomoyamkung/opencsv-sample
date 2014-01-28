package net.tomoyamkung.opencsv.sample03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import net.tomoyamkung.opencsv.Book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class ColumnPositionMappingStrategyTest {

	private Reader reader;
	private ColumnPositionMappingStrategy<Book> strategy;
	
	@Before
	public void setUp() throws Exception {
		strategy = new ColumnPositionMappingStrategy<Book>();
		strategy.setType(Book.class);
		strategy.setColumnMapping(Book.getFieldName());
		
		reader = new InputStreamReader(
				ColumnPositionMappingStrategyTest.class.getClassLoader().getResourceAsStream(
						ColumnPositionMappingStrategyTest.class.getSimpleName() + ".csv"), "UTF-8");
	}
	
	@After
	public void tearDown() throws Exception {
		if(reader != null) {
			reader.close();
		}
	}
	
	@Test
	public void ColumnPositionMappingStrategyを使ってCSVをBeanに変換する() throws Exception {
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
