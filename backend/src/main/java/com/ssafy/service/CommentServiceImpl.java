package com.ssafy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dao.CommentDAOImpl;
import com.ssafy.vo.Article;
import com.ssafy.vo.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	CommentDAOImpl commentDAO;

	@Autowired
	public CommentServiceImpl(CommentDAOImpl commentDAO) {
		this.commentDAO = commentDAO;
	}

	@Override
	public List<Comment> selectAllCommentsByArticleNo(int article_no) {
		return commentDAO.selectAllCommentsByArticleNo(article_no);
	}

	@Override
	public Comment selectCommentByCommentNo(int comment_no) {
		return commentDAO.selectCommentByCommentNo(comment_no);
	}
	
	@Override
	public boolean insertComment(Comment comment) {
		return commentDAO.insertComment(comment);
	}

	@Override
	public boolean deleteComment(int comment_no) {
		return commentDAO.deleteComment(comment_no);
	}

	@Override
	public boolean updateComment(Comment originComment, Comment changeComment) {
		if (!originComment.getComment_content().equals(changeComment.getComment_content())) {
			originComment.setComment_content(changeComment.getComment_content());
		}
		return commentDAO.updateComment(originComment);
	}

}
