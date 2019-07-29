package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Comment;

public interface CommentService {
	public List<Comment> selectAllCommentsByArticleNo(int article_no);
	Comment selectCommentByCommentNo(int comment_no);
	public boolean insertComment(Comment comment);
	public boolean deleteComment(int comment_no);
	public boolean updateComment(Comment originComment, Comment changeComment);
}
