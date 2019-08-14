package com.ssafy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Post;

public interface PostService {
	public Page<Post> findAllPosts(final Pageable pageable);
	public List<Post> findAll();
	public Optional<Post> findPostByPostId(final long postId); 
	public Post savePost(final Post post);
	public void deletePostByPostId(final long postId); 
	public long countPosts(); 
}
