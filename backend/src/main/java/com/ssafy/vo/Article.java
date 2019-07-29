package com.ssafy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class Article {
	int article_no;
	int account_no;
	String account_name;
	String article_title;
	String article_content;
	String article_imgsrc;
	int article_category_id;
	String article_write_date;
	int article_views;

	public Article() {
	}

	public Article(int article_no, int account_no, String account_name, String article_title, String article_content,
			String article_imgsrc, int article_category_id, String article_write_date, int article_views) {
		this.article_no = article_no;
		this.account_no = account_no;
		this.account_name = account_name;
		this.article_title = article_title;
		this.article_content = article_content;
		this.article_imgsrc = article_imgsrc;
		this.article_category_id = article_category_id;
		this.article_write_date = article_write_date;
		this.article_views = article_views;
	}
}
