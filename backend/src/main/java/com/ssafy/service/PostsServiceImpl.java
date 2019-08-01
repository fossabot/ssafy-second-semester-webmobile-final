package com.ssafy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostsRepository;
import com.ssafy.vo.Posts;

@Service
public class PostsServiceImpl implements PostsService {

	@Autowired
	PostsRepository postsRespository;

	@Override
	public int countPosts() {
		return (int) postsRespository.count();
	}

	@Override
	public List<Posts> findAll() {
		return postsRespository.findAll();
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
	public Optional<Posts> findPostById(int postId) {
		postsRespository.updatePostViewsByPostId(postId);
		return postsRespository.findById(postId);
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
	public boolean deletePostById(int postId) {
		Optional<Posts> optional = postsRespository.findById(postId);
		if (!optional.isPresent()) {
			return false;
		}
		postsRespository.deleteById(postId);
		return true;
	}
}
