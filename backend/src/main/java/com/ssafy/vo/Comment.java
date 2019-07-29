package com.ssafy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@ToString
public class Comment {
	int comment_no;
	String comment_content;
	String comment_write_date;
	int article_no;
	int account_no;
	String account_name;

	public Comment() {
	}
	
	public Comment(int comment_no, String comment_content, String comment_write_date, int article_no, int account_no,
			String account_name) {
		this.comment_no = comment_no;
		this.comment_content = comment_content;
		this.comment_write_date = comment_write_date;
		this.article_no = article_no;
		this.account_no = account_no;
		this.account_name = account_name;
	}
}
