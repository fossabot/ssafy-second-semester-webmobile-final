package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PostComments;

@Repository
public interface PostCommentsRepository extends JpaRepository<PostComments, Integer> {
	
}
