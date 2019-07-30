package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Posts;

@Repository
public interface PostCommentsRespository extends JpaRepository<Posts, Integer> {
	
}
