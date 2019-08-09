package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
	
}
