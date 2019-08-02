package com.ssafy.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ssafy.respository.PostRepository;
import com.ssafy.vo.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRespository;

	@Override
	public Page<Post> findAllPosts(final Pageable pageable) {
		return postRespository.findAll(pageable);
	}

	@Override
	@Transactional
	public Optional<Post> findPostByPostId(final int postId) {
		postRespository.updatePostViewsByPostId(postId);
		return postRespository.findById(postId);
	}

	@Override
	public Post savePost(final Post post) {
		return postRespository.save(post);
	}

	@Override
	public boolean deletePostByPostId(final int postId) {
		Optional<Post> optional = postRespository.findById(postId);
		if (!optional.isPresent()) {
			return false;
		}
		postRespository.deleteById(postId);
		return true;
	}
	
	@Override
	public int countPosts() {
		return (int) postRespository.count();
	}
	
}
