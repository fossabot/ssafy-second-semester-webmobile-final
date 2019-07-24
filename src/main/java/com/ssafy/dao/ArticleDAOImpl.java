package com.ssafy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Article;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	private SqlSession session;

	@Autowired
	public ArticleDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<Article> selectAllArticles(int article_category_id) {
		return session.selectList("selectAllArticles", article_category_id);
	}
	
	@Override
	public Article selectArticleByArticleNo(int article_no) {
		return session.selectOne("selectArticleByArticleNo", article_no);
	}

	@Override
	public boolean insertArticle(Article article) {
		try {
			session.insert("insertArticle", article);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteArticle(int article_no) {
		try {
			session.delete("deleteArticle", article_no);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateArticle(Article article) {
		try {
			session.update("updateArticle", article);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateArticleViews(int article_no) {
		try {
			session.update("updateArticleViews", article_no);
			return true;
		}catch(Exception e){
			return false;
		}
	}

}
