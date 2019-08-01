package com.ssafy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostCommentsRepository;
import com.ssafy.vo.PostComments;

@Service
public class PostCommentsServiceImpl implements PostCommentsService {

	@Autowired
	PostCommentsRepository postCommentsRespository;

	@Override
	public int countPostComments() {
		return (int) postCommentsRespository.count();
	}

	/**
	 * 댓글 게시글 읽어오기
	 */
	@Override
	public Optional<PostComments> findPostCommentById(int postCommentId) {
		return postCommentsRespository.findById(postCommentId);
	}

	/**
	 * Create & Update
	 */
	@Override
	public PostComments savePostComment(PostComments postComment) {
		return postCommentsRespository.save(postComment);
	}

	/**
	 * Delete
	 */
	@Override
	public boolean deletePostCommentById(int postCommentId) {
		Optional<PostComments> optional = postCommentsRespository.findById(postCommentId);
		if (!optional.isPresent()) {
			return false;
		}
		postCommentsRespository.deleteById(postCommentId);
		return true;
	}
}
