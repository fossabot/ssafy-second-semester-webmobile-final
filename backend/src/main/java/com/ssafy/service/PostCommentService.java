package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PostComment;

public interface PostCommentService {
	public Optional<PostComment> findPostCommentByPostCommentId(final int postId); 
	public PostComment savePostComment(final PostComment postComment); 
	public boolean deletePostCommentByPostCommentId(final int postCommentId);
	public int countPostComments(); 
}
