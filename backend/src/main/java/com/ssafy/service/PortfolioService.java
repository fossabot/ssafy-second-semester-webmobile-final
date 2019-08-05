package com.ssafy.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ssafy.vo.Portfolio;

public interface PortfolioService {
	public Page<Portfolio> findAllPortfolios(final Pageable pageable); 
	public Optional<Portfolio> findPortfolioByPortfolioId(final int portfolioId);
	public Portfolio savePortfolio(final Portfolio portfolios);
	public void deletePortfolioByPortfolioId(final int portfolioId);
	public int countPortfolios();
}
