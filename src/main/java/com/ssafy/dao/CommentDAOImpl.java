package com.ssafy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	private SqlSession session;

	@Autowired
	public CommentDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<Comment> selectAllCommentsByArticleNo(int article_no) {
		return session.selectList("selectAllCommentsByArticleNo", article_no);
	}

	@Override
	public boolean insertComment(Comment comment) {
		try {
			session.insert("insertComment", comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteComment(int comment_no) {
		try {
			session.delete("deleteComment", comment_no);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateComment(Comment comment) {
		try {
			session.update("updateComment", comment);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Comment selectCommentByCommentNo(int comment_no) {
		return session.selectOne("selectCommentByCommentNo", comment_no);
	}

}
