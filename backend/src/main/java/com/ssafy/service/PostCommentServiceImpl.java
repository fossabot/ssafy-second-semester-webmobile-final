package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostCommentRepository;
import com.ssafy.respository.PostRepository;
import com.ssafy.vo.Post;
import com.ssafy.vo.PostComment;

@Service
public class PostCommentServiceImpl implements PostCommentService {

	@Autowired
	PostRepository postRepostiory;
	
	@Autowired
	PostCommentRepository postCommentRespository;

	@Override
	public Optional<PostComment> findPostCommentByPostCommentId(final long postCommentId) {
		return postCommentRespository.findById(postCommentId);
	}

	@Override
	@Transactional
	public PostComment savePostComment(final long postId, final PostComment postComment) {
		Post post =postRepostiory.findById(postId).get();
		post.addPostComment(postComment);
		return postCommentRespository.save(postComment);
	}

	@Override
	@Transactional
	public void deletePostCommentByPostCommentId(final long postCommentId) {
		Post post = postCommentRespository.findById(postCommentId).get().getPost();
		post.removePostComment(postCommentRespository.findById(postCommentId).get());
		postCommentRespository.deleteById(postCommentId);
		return;
	}
	
	@Override
	public long countPostComments() {
		return postCommentRespository.count();
	}
	
}
