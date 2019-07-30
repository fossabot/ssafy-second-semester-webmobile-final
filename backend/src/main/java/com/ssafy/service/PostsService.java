package com.ssafy.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Posts;

public interface PostsService {
	public int count(); // 전체 게시글
	public Page<Posts> findAllPosts(Pageable pageable); // 게시글 리스트 불러오기
	public Optional<Posts> findPostById(int post_id); // 게시글 읽기
	public Posts savePost(Posts post); // 게시글 수정, 삽입
	public boolean deletePostById(int post_id); // 게시글 삭제
}
