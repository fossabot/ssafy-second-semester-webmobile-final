package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.ArticleDAOImpl;
import com.ssafy.vo.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	ArticleDAOImpl articleDAO;

	@Autowired
	public ArticleServiceImpl(ArticleDAOImpl articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public List<Article> selectAllArticles(int article_category_id) {
		return articleDAO.selectAllArticles(article_category_id);
	}

	@Override
	@Transactional
	public Article selectArticleByArticleNo(int article_no) {
		articleDAO.updateArticleViews(article_no);
		Article article = articleDAO.selectArticleByArticleNo(article_no);
		return article;
	}

	@Override
	public boolean insertArticle(Article article) {
		return articleDAO.insertArticle(article);
	}

	@Override
	public boolean deleteArticle(int article_no) {
		return articleDAO.deleteArticle(article_no);
	}

	@Override
	public boolean updateArticle(Article originArticle, Article changeArticle) {
		if (!originArticle.getArticle_title().equals(changeArticle.getArticle_title())) {
			originArticle.setArticle_title(changeArticle.getArticle_title());
		}
		if (!originArticle.getArticle_content().equals(changeArticle.getArticle_content())) {
			originArticle.setArticle_content(changeArticle.getArticle_content());
		}
		if (!originArticle.getArticle_imgsrc().equals(changeArticle.getArticle_imgsrc())) {
			originArticle.setArticle_imgsrc(changeArticle.getArticle_imgsrc());
		}
		return articleDAO.updateArticle(originArticle);
	}

}
