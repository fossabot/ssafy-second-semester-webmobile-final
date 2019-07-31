package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vo.Portfolios;

@Repository
public interface PortfoliosRespository extends JpaRepository<Portfolios, Integer> {
	
	@Modifying
	@Transactional
	@Query(value="delete from portfolios where portfolio_id = 1", nativeQuery = true)
	public void test();
}
