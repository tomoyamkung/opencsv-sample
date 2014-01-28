package net.tomoyamkung.opencsv.sample02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

public class StartReadingLineTest {
	
	private CSVReader sut;

	@Before
	public void setUp() throws Exception {
		int lineNumber = 2; // ファイルの読み込み開始行
		
		sut = new CSVReader(new InputStreamReader(
				StartReadingLineTest.class.getClassLoader().getResourceAsStream(
						StartReadingLineTest.class.getSimpleName() + ".csv"), "UTF-8"),
						CSVParser.DEFAULT_SEPARATOR, // デフォルトのセパレータは「カンマ」
		                CSVParser.DEFAULT_QUOTE_CHARACTER, // デフォルトの囲み文字はダブルクォート
		                lineNumber); // 第4引数に、ファイルの読み込み開始行を指定する
	}
	
	@After
	public void tearDown() throws Exception {
		if(sut != null) {
			sut.close();
		}
	}

	@Test
	public void ファイルを3行目から読み込める() throws Exception {
		List<String[]> actual = sut.readAll();
		
		assertThat("3行目から読み込むので、読み込んだファイルの行数は2である", actual.size(), is(2));
		
		assertThat("3行目の内容が正しい", Arrays.toString(actual.get(0)), is("[line3, ccc, 333]"));
		assertThat("4行目の内容が正しい", Arrays.toString(actual.get(1)), is("[line4, ddd, 444]"));
	}

}
