package com.ssafy.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Post;

public interface PostService {
	public Page<Post> findAllPosts(final Pageable pageable);
	public Optional<Post> findPostByPostId(final int postId); 
	public Post savePost(final Post post);
	public boolean deletePostByPostId(final int postId); 
	public int countPosts(); 
}
