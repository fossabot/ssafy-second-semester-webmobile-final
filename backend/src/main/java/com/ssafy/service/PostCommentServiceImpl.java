package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostCommentRepository;
import com.ssafy.vo.PostComment;

@Service
public class PostCommentServiceImpl implements PostCommentService {

	@Autowired
	PostCommentRepository postCommentRespository;

	@Override
	public Optional<PostComment> findPostCommentByPostCommentId(final int postCommentId) {
		return postCommentRespository.findById(postCommentId);
	}

	@Override
	public PostComment savePostComment(final PostComment postComment) {
		return postCommentRespository.save(postComment);
	}

	@Override
	public void deletePostCommentByPostCommentId(final int postCommentId) {
		postCommentRespository.deleteById(postCommentId);
		return;
	}
	
	@Override
	public int countPostComments() {
		return (int) postCommentRespository.count();
	}
	
}
