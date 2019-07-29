package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Article;

public interface ArticleService {
	public List<Article> selectAllArticles(int article_category_id);
	public Article selectArticleByArticleNo(int article_no);
	public boolean insertArticle(Article article);
	public boolean deleteArticle(int article_no);
	public boolean updateArticle(Article originArticle, Article changeArticle);
}
