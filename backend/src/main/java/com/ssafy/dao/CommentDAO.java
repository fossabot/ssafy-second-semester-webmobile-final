package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Comment;

public interface CommentDAO {
	public List<Comment> selectAllCommentsByArticleNo(int article_no);
	public Comment selectCommentByCommentNo(int comment_no);
	public boolean insertComment(Comment comment);
	public boolean deleteComment(int comment_no);
	public boolean updateComment(Comment comment);
}
