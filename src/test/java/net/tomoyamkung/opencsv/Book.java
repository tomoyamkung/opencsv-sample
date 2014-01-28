package net.tomoyamkung.opencsv;

/**
 * 本を表すクラス。
 * 
 * CSV の内容をマッピングするためのクラス。
 * 
 * @author tomoyamkung
 *
 */
public class Book {
	
	/**
	 * タイトル。
	 */
	private String title;
	
	/**
	 * 出版社。
	 */
	private String publisher;

	/**
	 * ページ数。
	 */
	private int numberOfPages;

	/**
	 * デフォルトコンストラクタを定義しないと <code>InstantiationException</code> が発生し parse に失敗する。
	 */
	public Book() {}
	
	public Book(String title, String publisher, int numberOfPages) {
		this.title = title;
		this.publisher = publisher;
		this.numberOfPages = numberOfPages;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 定義しないと <code>NullPointerException</code> が発生し parse に失敗する。
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	/**
	 * 定義しないと <code>NullPointerException</code> が発生し parse に失敗する。
	 * 
	 * @param publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	/**
	 * 定義しないと <code>NullPointerException</code> が発生し parse に失敗する。
	 * 
	 * @param numberOfPages
	 */
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	/**
	 * フィールド名を取得する。
	 * 
	 * @return
	 */
	public static final String[] getFieldName() {
		return new String[]{"title", "publisher", "numberOfPages"};
	}
}
