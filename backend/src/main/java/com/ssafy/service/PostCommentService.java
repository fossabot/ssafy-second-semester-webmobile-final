package com.ssafy.service;

import java.util.Optional;

import com.ssafy.vo.PostComment;

public interface PostCommentService {
	public Optional<PostComment> findPostCommentByPostCommentId(final long postId); 
	public PostComment savePostComment(final long postCommentId, final PostComment postComment); 
	public void deletePostCommentByPostCommentId(final long postCommentId) throws Exception;
	public long countPostComments(); 
}
