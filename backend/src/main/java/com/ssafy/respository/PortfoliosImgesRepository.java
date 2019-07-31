package com.ssafy.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.vo.PortfolioImages;

@Repository
public interface PortfoliosImgesRepository extends JpaRepository<PortfolioImages, Integer> {

}
