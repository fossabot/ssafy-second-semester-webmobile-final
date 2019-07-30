package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostsRespository;
import com.ssafy.vo.Posts;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	PostsRespository postsRespository;

	@Override
	public int count() {
		return (int) postsRespository.count();
	}

	@Override
	public Page<Posts> findAllPosts(Pageable pageable) {
		return postsRespository.findAll(pageable);
	}

	/**
	 * 포스트 게시글 읽어오기 (조회수 올리기 + 읽어오기)
	 */
	@Override
	@Transactional
	public Optional<Posts> findPostById(int post_id) {
		postsRespository.updatePostViewsByPostId(post_id);
		return postsRespository.findById(post_id);
	}

	/**
	 * Create & Update
	 */
	@Override
	public Posts savePost(Posts post) {
		return postsRespository.save(post);
	}

	/**
	 * Delete
	 */
	@Override
	public boolean deletePostById(int post_id) {
		Optional<Posts> optional = postsRespository.findById(post_id);
		if (!optional.isPresent()) {
			return false;
		}
		postsRespository.deleteById(post_id);
		return true;
	}
}
