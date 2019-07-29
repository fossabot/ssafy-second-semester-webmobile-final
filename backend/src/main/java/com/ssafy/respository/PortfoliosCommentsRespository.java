package com.ssafy.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.Posts;

@Repository
public interface PortfoliosCommentsRespository extends JpaRepository<Posts, Integer> {
	
	@Query("SELECT p FROM Post p" + " WHERE p.post_title LIKE %?1%" + " OR p.post_content LIKE %?1%")
	public List<Posts> searchByQuery(String query);
}
