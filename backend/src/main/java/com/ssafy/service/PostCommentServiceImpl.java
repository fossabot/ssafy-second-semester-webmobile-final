package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostCommentRepository;
import com.ssafy.respository.PostRepository;
import com.ssafy.vo.PostComment;

@Service
public class PostCommentServiceImpl implements PostCommentService {

	@Autowired
	PostCommentRepository postCommentRespository;
	
	@Autowired
	PostRepository postRepostiory;

	@Override
	public Optional<PostComment> findPostCommentByPostCommentId(final long postCommentId) {
		return postCommentRespository.findById(postCommentId);
	}

	@Override
	public PostComment savePostComment(final long postId, final PostComment postComment) {
		postRepostiory.findById(postId).get().addPostComments(postComment);
		return postCommentRespository.save(postComment);
	}

	@Override
	public void deletePostCommentByPostCommentId(final long postCommentId) {
		postCommentRespository.deleteById(postCommentId);
		return;
	}
	
	@Override
	public long countPostComments() {
		return postCommentRespository.count();
	}
	
}
