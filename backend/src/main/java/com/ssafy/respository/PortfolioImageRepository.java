package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PortfolioImage;

@Repository
public interface PortfolioImageRepository extends JpaRepository<PortfolioImage, Long> {
	
}
