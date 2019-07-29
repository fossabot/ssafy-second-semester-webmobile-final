package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Article;

public interface ArticleDAO {
	public List<Article> selectAllArticles(int article_category_id);
	public Article selectArticleByArticleNo(int article_no);
	public boolean insertArticle(Article article);
	public boolean deleteArticle(int article_no);
	public boolean updateArticle(Article article);
	public boolean updateArticleViews(int article_no);
}
