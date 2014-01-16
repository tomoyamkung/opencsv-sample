package net.tomoyamkung.opencsv.sample01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

public class OpenCSVTest {
	
	private CSVReader sut;

	@Before
	public void setUp() throws Exception {
		sut = new CSVReader(new InputStreamReader(
				OpenCSVTest.class.getClassLoader().getResourceAsStream("test.csv"),"UTF-8"));
	}
	
	@After
	public void tearDown() throws Exception {
		if(sut != null) {
			sut.close();
		}
	}

    @Test
    public void test() throws Exception {
        // exercise
        String[] actual = sut.readNext();

        // verify
        assertThat("カラム数が4であること", actual.length, is(4));
        assertThat("1カラム目の内容が正しいこと", actual[0], is("囲われていないカラム")); 
        assertThat("カラムにハイフンがあっても取得できること", actual[1], is("01-01")); 
        assertThat("カラムにカンマがあっても取得できること", actual[2], is("02,02")); 
        assertThat("カラムにスペースがあっても取得できること", actual[3], is("2013/07/01 10:10:10")); 
    }

}
