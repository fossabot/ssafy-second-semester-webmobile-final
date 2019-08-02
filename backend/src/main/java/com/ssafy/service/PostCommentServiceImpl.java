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
	public boolean deletePostCommentByPostCommentId(final int postCommentId) {
		Optional<PostComment> optional = postCommentRespository.findById(postCommentId);
		if (!optional.isPresent()) {
			return false;
		}
		postCommentRespository.deleteById(postCommentId);
		return true;
	}
	
	@Override
	public int countPostComments() {
		return (int) postCommentRespository.count();
	}
	
}
