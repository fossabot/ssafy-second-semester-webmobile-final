package com.ssafy.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PortfolioComments;

@Repository
public interface PortfoliosCommentsRespository extends JpaRepository<PortfolioComments, Integer> {

	@Query(value = "select * from portfolio_comments where portfolio_id = ?1", nativeQuery = true)
	List<PortfolioComments> findByPortfolioId(int portfolio_id);
	
}
