package com.ssafy.common;

import lombok.Getter;

@Getter
public enum ArticleType {
	
	portfolios(1),posts(2);
	
	private int articleType;
	ArticleType(int article_category_name){
		this.articleType = article_category_name;
	}
}
