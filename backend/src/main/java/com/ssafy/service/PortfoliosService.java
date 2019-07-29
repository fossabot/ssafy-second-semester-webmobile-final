package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Portfolios;

public interface PortfoliosService {
	public List<Portfolios> selectAllArticles(int article_category_id);
	public Portfolios selectArticleByArticleNo(int article_no);
	public boolean insertArticle(Portfolios article);
	public boolean deleteArticle(int article_no);
	public boolean updateArticle(Portfolios originArticle, Portfolios changeArticle);
}
