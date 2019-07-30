package com.ssafy.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Posts;

@Repository
public interface PostsRespository extends JpaRepository<Posts, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE posts SET post_views = post_views + 1 WHERE post_id = ?1", nativeQuery = true)
	public void updatePostViewsByPostId(int postId);
}
