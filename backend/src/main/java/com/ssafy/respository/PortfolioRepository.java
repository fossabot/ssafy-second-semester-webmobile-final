package com.ssafy.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vo.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE portfolios SET portfolio_views = portfolio_views + 1 WHERE portfolio_id = ?1", nativeQuery = true)
	public void updatePortfolioViewsByPortfolioId(long portfolioId);
	
}
